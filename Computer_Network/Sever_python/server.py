import http_parser
import socket
HOST = '172.30.1.8'
PORT = 80

#주소체계로 IPv4 , 소켓 타입으로 tcp 사용 
server_socket = socket.socket(socket.AF_INET , socket.SOCK_STREAM)
server_socket.setsockopt(socket.SOL_SOCKET , socket.SO_REUSEADDR , 1)
server_socket.bind((HOST , PORT))


while True:
    #Recive request_http
    server_socket.listen()
    client_socket , addr = server_socket.accept()
    print('Connected by' , addr)
    request_http = client_socket.recv(512).decode()
    print(request_http)
    #Parse request_http and send response_http
    response_data = http_parser.response_http_packet(request_http)
    response_http = response_data.get_response()
    client_socket.send(response_http.encode())
    client_socket.close()
    break

server_socket.close()

    








