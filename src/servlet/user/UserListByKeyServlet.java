package servlet.user;

import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListByKeyServlet")
public class UserListByKeyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取关键字参数值
        String keywords = request.getParameter("keywords");
        //创建用户服务层对象 查询该用户后传到前台
        //实例化service层中UserService对象
        UserService service = new UserServiceImpl();
        //调用service层中的findUserListByKey方法
        List<User> userList =  service.findUserListByKey(keywords);
        request.setAttribute("userList",userList);

        request.getRequestDispatcher("admin_user.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
