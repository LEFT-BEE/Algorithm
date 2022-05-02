import os 
import time

# Request_http Parser
class parser_http_packet:
    def __init__(self , http):
        self.http  = http 
        split_http = self.http.split('\r\n')
        self.start_line = split_http[0]
        self.data = self.http.split('\r\n\r\n')[1]
        self.method = self.get_method()
        self.url = self.get_url()
        self.version = self.get_version()
        self.header = self.parsed_header()
    #header 분석 dictionary type으로 만들어준다.
    def parsed_header(self):
        header = dict()
        head_lines = self.http.split('\r\n\r\n')[0]
        for head_line in head_lines.split('\r\n')[1:]:
            try:
                key , value = head_line.split(':')
                header[key.strip()] = value.strip()
            except:
                pass
        return header

    def get_method(self):
        method = self.start_line.split(" ")[0]
        return method

    def get_url(self):
        split_line = self.start_line.split(" ")
        if len(split_line) == 3:
            return split_line[1]
        return None
        
    def get_version(self):
        split_line = self.start_line.split(" ")
        if len(split_line) == 3:
            return split_line[2]
        return split_line[1]





        
#http Parser를 통해 얻은 정보를 토대로 그에 맞게 실행 및 Response_http 생성 
class response_http_packet:
    def __init__(self , http):
        self.parsed_http = parser_http_packet(http)#Parsed Request_http 
        #Response_http 에 들어갈 data , header , start_line
        self.data = ""
        self.start_line  = ""
        self.response_header = dict()
   
        if "Content-Language" in self.parsed_http.header.keys():
            self.language = self.parsed_http.header["Content-Language"]
        if "Cache-Control" in self.parsed_http.header.keys():
            self.Cache_Control = self.parsed_http.header["Cache-Control"]
        if self.parsed_http.data:
            self.content_type = self.parsed_http.header["Content-type"]
            self.content_len = self.parsed_http.header["Content-length"]
    
    #Request의 Method에 따라 그에 맞는 상황을 처리하고 성공여부에 따라 State , Content를 결정한다.
    def response_startline(self):
        state = '1004'
        contents = None

        ######## Reqeust의 요청이 GET일경우 ##########
        if self.parsed_http.method == "GET":
            try:
                filepath = self.parsed_http.url
                with open(filepath , 'r') as file:
                    if self.parsed_http.header["Content-using"] == "get_html": #만약 사용자의 html file을 요구할 경우 
                        if not self.check_user(): #id와 password가 다를 시 Permission error 발생
                            raise PermissionError
                    self.data = file.read()
                contents , state = 'OK.' , '200'
                if self.parsed_http.header["Content-using"] == "get_html":
                    self.response_header['Content-type'] =  "txt/html"
                else:
                    self.response_header['Content-type'] =  "txt/plain"
            except:
                contents , state = self.exception_hander(e)
         
        ######## Reqeust의 요청이 POST일경우 ##########
        elif self.parsed_http.method == "POST":
            filepath = self.parsed_http.url
            i = 2
            #파일명이 동일한 경우 새로운 파일 생성 
            while os.path.isfile(filepath):
                filepath = filepath.split('.')[0] + f"({i})." + filepath.split('.')[1]
                i+=1
            if self.parsed_http.header["Content-using"] == "register":
                try:
                    with open(filepath , 'w') as file:
                        file.write(self.parsed_http.data)
                    contents , state = 'Created.' , '201'
                    self.data = "Successfully register your data"
                    self.response_header['Content-type'] =  "txt/plain"
                except Exception as e:
                    contents , state = self.exception_hander(e)


        ######## Reqeust의 요청이 PUT일경우 ##########
        elif self.parsed_http.method == "PUT":
            try:
                filepath = self.parsed_http.url
                with open(filepath , 'r') as file:
                    data = file.read()
                # password 변경 요청일 경우 데이터 변경 
                if self.parsed_http.header["Content-using"] == "change_password":
                    data = data.split("\n")[0] + "\npassword : " + self.parsed_http.data
                # 그 외 경우 미구현 
                else:
                    pass
                with open(filepath , 'w') as file:
                    file.write(data)
                contents , state= 'No cotontent.' , '204'
                self.data = "Succesfully change!"
                self.response_header['Content-type'] =  "txt/plain"
            except Exception as e:
                contents , state = self.exception_hander(e)

        ######## Reqeust의 요청이 DELETE일경우 ##########
        elif self.parsed_http.method == "DELETE":
            try:
                filepath = self.parsed_http.url
                os.remove(filepath)
                contents  , state = 'OK.' , '202'
                self.response_header['Content-type'] =  "txt/plain"
            except Exception as e:
                contents , state = self.exception_hander(e)
                
        else:
            contents , state = 'Bad request.' , '400'
        
        self.start_line = self.parsed_http.version + " " +  state + " " + contents #Response_http의 start_line 생성 
        
    def exception_hander(self, e):
        if type(e) == FileNotFoundError:
            return "Not found." , '404'
        elif type(e) == PermissionError:
            return "Unauthorized." , '401'        
        else:
            return 'Bad request' , '400'

    def check_user(self):
        filepath = "login_db.txt"                    
        with open(filepath , 'r') as file:
            server_data = file.read()
        server_id , server_password = server_data.split('\n')
        server_id = server_id.split(':')[1].strip()
        server_password = server_password.split(':')[1].strip()
        id , password = self.parsed_http.data.split(' ')
        
        return id == server_id and password == server_password

    #Response_header 생성
    def make_response_header(self):
        self.response_header['Server'] = 'Ubuntu os server Host : 172.30.1.8'
        self.response_header['Date'] = str(time.strftime('%c', time.localtime(time.time())))
        if self.data:
            self.response_header['Content-length'] = str(len(self.data))
            
    #Response_http 생성 및 전달
    def get_response(self):
        self.response_startline()
        self.make_response_header()
        http_response_header = ""
        for key , value in self.response_header.items():
                http_response_header += key + " : " + value + "\r\n"
        return self.start_line + '\r\n' + http_response_header + '\r\n' + self.data
                
        

        

        
        




            



            



