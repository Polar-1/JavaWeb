package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//实例化service层中UserService对象
		UserService service = new UserServiceImpl();
		//获取用户账号和密码值
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		User user = service.loginByNameAndPassword(userName, passWord);
		//通过user_status判断用户账号
		if(user != null && "1".equals(user.getUser_status())) {
			HttpSession session = request.getSession();
			
			session.setAttribute("name", user);
			session.setAttribute("isLogin", "1");
			
			response.sendRedirect("indexServlet");
			
		}else{
			//前端响应
			PrintWriter out = response.getWriter();
			//弹出窗口
			out.write("<script>");
			out.write("alert('用户名或密码错误！');");
			out.write("location.href='login.jsp'");
			out.write("</script>");
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
