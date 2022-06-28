package servlet.user;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delUserByIdServlet")
public class DelUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户id
        String uid = request.getParameter("id");
        int uid2 = Integer.valueOf(uid);

        //创建用户服务层对象
        //实例化service层中UserService对象
        UserService service = new UserServiceImpl();
        //调用service层中delUserById方法
        service.delUserById(uid2);

        request.getRequestDispatcher("/userListServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
