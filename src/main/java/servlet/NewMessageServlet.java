package servlet; 
 
import java.io.IOException;
import java.util.Calendar;
import entity.ChatMessage;
import entity.ChatUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
 
@WebServlet("/send_message.do")
public class NewMessageServlet extends ChatServlet { 
 
	private static final long serialVersionUID = 1L; 
 
  protected void doPost(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException { 
	  // По умолчанию используется кодировка ISO-8859. Так как мы  
	  // передаем данные в кодировке UTF-8 
	  // то необходимо установить соответствующую кодировку HTTP-запроса 
	  request.setCharacterEncoding("UTF-8");     
	  String pname=null;
    // Извлечь из HTTP-запроса параметр 'message' 
    String message = (String)request.getParameter("message"); 
    // Если сообщение не пустое, то 
    if (message!=null && !"".equals(message)) { 
    	String privatem=(String)request.getSession().getAttribute("privatem");
    	if(privatem != null && !"toall".equals(privatem))
    		pname=privatem;
      // По имени из сессии получить ссылку на объект ChatUser 
      ChatUser author = activeUsers.get((String) request.getSession().getAttribute("name"));  
      synchronized (messages) { 
        // Добавить в список сообщений новое 
        messages.add(new ChatMessage(message, author,
        		Calendar.getInstance().getTimeInMillis(),pname)); 
      } 
    } 
    // Перенаправить пользователя на страницу с формой сообщения 
    response.sendRedirect("/chat/compose_message.htm"); 
  } 
 
} 