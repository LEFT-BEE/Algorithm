Hellow this is readme file
This server can accpet 'GET', 'PUT', 'POST', 'DELETE' method
If you get this messege, connection is fine

if you want register your data, follow bellow instruction.
-----------------------------------------------
1. Use 'POST' method and write your data
2. follow this format "id : example_id\npassword: example_password
3. you must to put "Content-using : register" data in header!!

3. After your data successfully save if you do same thing, server make new file again 

4. You can change your id and password by using "PUT" method
5. If you want change id, put "Content-using :change_id" in header!!
6. If you done register your data in server, you can access todolist html
7. If your want to delete your data use "DELETE" methodself.response_header['Content-type'] =  "txt/plain"
-----------------------------------------------