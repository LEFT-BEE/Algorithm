from client_packet import request_http_packet
#HOST = '172.30.1.8'
HOST  = '127.0.0.1'

text = '''
###-------------------- GET EXAMPLE -------------------- ###
# 로그인과 todolist 페이지를 불러오기 위한 readme파일을 불러온다.
# Response 의 데이터에 readme.txt의 내용이 담긴다.
# 성공적으로 불러올시 응답으로 200 OK. 를 수신받는다.
###----------------------------------------------------------###
'''

print(text)
reqeust_get = request_http_packet()
reqeust_get.make_start_line('GET' , "readme.txt"  , '1.0')
feature = {"Content-using" : "get_readme"}
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)
input()

text = '''
###------------------- POST EXAMPLE--------------------------###
# 회원가입을 위해 user data를 서버에 저장한다.
# 데이터를 주는 방벙븐 request_http_packet 모듈을 생성할 때 같이 전해주고, 
# Header에 Content-using : register 라고 알린다.
# POST 명령어를 반복해서 실행할 시 user data가 계속 생성된다. ex) login_db.txt , login_db(2).txt ...
# 성공적으로 불러올시 응답으로 201 Creadted. 를 수신받는다.
###----------------------------------------------------------###
'''

print(text)
reqeust_get = request_http_packet('id : gkstmdwls99\npassword : 1234')
reqeust_get.make_start_line('POST' , "login_db.txt")
feature = {"Content-Language" : "Eng" ,"Content-using" : "register" ,"Content-type" : "text/plain" , "Host" : '127.30.1.32' , "Cache-Control" : "No-store"}
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)
input()

text = '''
###------------------- GET EXAMPLE--------------------------###
# user의 todolist 정보를 담고 있는 html파일을 불러온다
# data 에 id 와 password 정보를 넘겨주고,
# Header에 Content0using : get_html 이라고 알린다.
# 성공적으로 불러올시 응답으로 200 OK. 를 수신받는다.
###----------------------------------------------------------###
'''
print(text)
reqeust_get = request_http_packet("gkstmdwls99 1234")
reqeust_get.make_start_line('GET' , "user_todo_list.html"  , '1.0')
feature = {"Content-Language" : "Eng" ,"Content-using" : "get_html" ,"Content-type" : "text/plain" , "Host" : '127.30.1.32' , "Cache-Control" : "No-store"}
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)
input()


text = '''
###------------------- PUT EXAMPLE---------------------------###
# password를 바꾸는 예제이다.
# Data에 변경할 password를 입력하고,
#  Content-using : change_password 라고 알린다. 
# 성공적으로 불러올시 응답으로 204 No content. 를 수신받는다.
###----------------------------------------------------------###
'''

print(text)
reqeust_get = request_http_packet('@12345678')
reqeust_get.make_start_line('PUT' , "login_db.txt")
feature = {"Content-Language" : "Eng" ,"Content-using" : "change_password" ,"Content-type" : "text/plain" , "Host" : '127.30.1.32' , "Cache-Control" : "No-store"}
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)
input()


text = '''
###------------------- GET EXAMPLE---------------------------###
# 만약 id 와 비밀번호가 틑릴시 Permission error가 발생한다.
# Permission error 발생시 401 Unauthorized. 를 수신받는다
#
###----------------------------------------------------------###
'''
print(text)
reqeust_get = request_http_packet("gkstmdwls99 1234")
reqeust_get.make_start_line('GET' , "user_todo_list.html"  , '1.0')
feature = {"Content-Language" : "Eng" ,"Content-using" : "get_html" ,"Content-type" : "text/plain" , "Host" : '127.30.1.32' , "Cache-Control" : "No-store"}
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)
input()


text = '''
###------------------- DELETE EXAMPLE------------------------###
# server에 존재하는 정보를 삭제한다.
# 성공적으로 제거시 202 OK. 를 수신받는다
#
###----------------------------------------------------------###
'''
print(text)
reqeust_get = request_http_packet()
reqeust_get.make_start_line('DELETE' , "login_db.txt")
feature = {"Host" : '127.0.0.1' }
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)
input()

text = '''
###------------------- ERROR EXAMPLE--------------------------###
# Method 가 "CET" 로 주었을 시 매칭되는 method가 존재하지 않으므로 에러발생
# 에러발생시 400 Bad reqeust를 수신받는다
#
###----------------------------------------------------------###
'''
print(text)
reqeust_get = request_http_packet()
reqeust_get.make_start_line('CET' , "readme.txt"  , '1.0')
response = reqeust_get.send_request(HOST, 80)
print(response)
input()

text = '''
###------------------- ERROR EXAMPLE--------------------------###
# 경로를 "readme.txttttt" 를 주었을 시 file을 찾을 수 없으므로 에러발생
# 에러발생시 404 Not found를 수신받는다.
#
###----------------------------------------------------------###
'''

print(text)
reqeust_get = request_http_packet()
reqeust_get.make_start_line('GET' , "readme.txttttt"  , '1.0')
feature = {"fin_token" : 'True' }
reqeust_get.make_header(feature)
response = reqeust_get.send_request(HOST, 80)
print(response)













