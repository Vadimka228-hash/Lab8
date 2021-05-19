package servlet; 
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import entity.ChatMessage;
import entity.ChatUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
 
@WebServlet("/ChatServlet")//если не вставить эьу строчку,то eclipse не видит сервлета
public class ChatServlet extends HttpServlet { 
   
  private static final long serialVersionUID = 1L; 
   
  // Карта текущих пользователей 
  protected HashMap<String, ChatUser> activeUsers; 
  // Список сообщений чата 
  protected ArrayList<ChatMessage> messages; 
 
  @SuppressWarnings("unchecked") 
  public void init() throws ServletException { 
    // Вызвать унаследованную от HttpServlet версию init() 
    super.init(); 
    // Извлечь из контекста карту пользователей и список сообщений 
    activeUsers = (HashMap<String, ChatUser>) getServletContext().getAttribute("activeUsers"); 
    messages = (ArrayList<ChatMessage>) getServletContext().getAttribute("messages"); 
    // Если карта пользователей не определена ... 

    if (activeUsers==null) { 
      // Создать новую карту 
      activeUsers = new HashMap<String, ChatUser>(); 
      // Поместить ее в контекст сервлета,  
// чтобы другие сервлеты могли до него добраться 
      getServletContext().setAttribute("activeUsers", activeUsers);       
    } 
    // Если список сообщений не определен ... 
    if (messages==null) { 
      // Создать новый список 
      messages = new ArrayList<ChatMessage>(100); 
      // Поместить его в контекст сервлета,  
// чтобы другие сервлеты могли до него добрать 
      getServletContext().setAttribute("messages", messages); 
    } 
  }
  //////
  public boolean checklogint(HttpServletRequest request, HttpServletResponse response) throws IOException {
	  boolean b=false;
	  for (ChatUser aUser: activeUsers.values()) {
		  if(aUser.getName().equals((String)request.getSession().getAttribute("name"))){
			  b = true;
			  }
		  }
	  if(!b)
		  response.sendRedirect(response.encodeRedirectURL("/chat/login.do"));
	  return b;
	  }
  /////
  } 