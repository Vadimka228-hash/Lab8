package servlet; 
 
import java.io.IOException;
import java.io.PrintWriter;

import entity.ChatMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
 
@WebServlet("/messages.do")
public class MessageListServlet extends ChatServlet { 

	private static final long serialVersionUID = 1L; 
        
  protected void doGet(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException { 
    // Установить кодировку HTTP-ответа UTF-8 
    response.setCharacterEncoding("utf8"); 
    // Получить доступ к потоку вывода HTTP-ответа 
    PrintWriter pw = response.getWriter(); 
    // Записть в поток HTML-разметку страницы 
    pw.println("<!DOCTYPE html>\n<html><head><meta http-equiv='Content-Type' " +
    "content='text/html; charset=utf-8'/><meta http-equiv='refresh' " +
    		"content='10'></head>"); 
    pw.println("<body>"); 
    pw.println("<div><strong>" + (String)request.getSession().getAttribute("name") +": Online"+ "</strong></div>");
    // В обратном порядке записать в поток HTML-разметку для каждого сообщения 
    for (int i=messages.size()-1; i>=0; i--) { 
      ChatMessage aMessage = messages.get(i);
      String priv = messages.get(i).getPrivatem();
      String auth = messages.get(i).getAuthor().getName();
      String uname = (String)request.getSession().getAttribute("name");
      
      if(priv != null){
    	  if(priv.equals(uname)||auth.equals(uname)){
				pw.println("<div><strong>" + aMessage.getAuthor().getName()
						+ "</strong>: " + aMessage.getMessage() + "</div>");
			}
		}else{
			pw.println("<div><strong>" + aMessage.getAuthor().getName()
					+ "</strong>: " + aMessage.getMessage() + "</div>"); 
			}     
      }
    pw.println("</body></html>"); 
    } 
}