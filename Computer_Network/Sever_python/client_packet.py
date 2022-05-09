from http import client
import socket
#Request 파일 생성 
class request_http_packet:
    def __init__(self ,data = None):
        #전송할 데이터는 처음 초기화 할때 넣어준다.
        self.start_line = ""
        self.header = ""
        self.request = ""
        self.data = data
    #start line생성
    def make_start_line(self , method , target, ver ='1.0'):
        self.start_line = method
        if len(target) > 0:
            self.start_line = self.start_line + ' ' + target
        self.start_line = self.start_line + ' HTTP/' + ver +'\r\n'
        return self.start_line
    #header 생성
    def make_header(self, header = None):
        if header:
            self.header = ""
            for key , value in header.items():
                self.header += key + " : " + value + "\r\n"
            if self.data:
                self.header += 'Content-length' + " : " + str(len(self.data)) + "\r\n"
            return self.header
    #완성된 request_http 전달
    def get_request(self):
        if self.data:
            self.request = self.start_line + self.header + '\r\n'+self.data
        else:
            self.request = self.start_line + self.header+'\r\n'
        return self.request
    # tcp 통신 및 http 데이터 서버에 전송 
    def send_request(self , address ,  port):
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
        client_socket.connect((address , port))
        cmd = self.get_request()
        client_socket.send(cmd.encode())
        response_http = client_socket.recv(2048)
        
        client_socket.close()
        return response_http.decode()