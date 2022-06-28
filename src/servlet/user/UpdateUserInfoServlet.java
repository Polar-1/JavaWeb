package servlet.user;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import entity.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateUserInfoServlet")
public class UpdateUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");
        if (user != null) {
            //获取用户id、性别、昵称参数值
            int userId = user.getUser_id();
            String sex = request.getParameter("sex");
            String nickname = request.getParameter("nickname");
            //创建用户服务层对象 查询该用户后传到前台
            UserService service = new UserServiceImpl();
            service.updateInfoById(userId,sex,nickname);
            //前端响应
            PrintWriter out = response.getWriter();
            //弹出窗口
            out.write("<script>");
            out.write("alert('修改成功！');");
            out.write("location.href='toMyInfoServlet'");
            out.write("</script>");
            out.close();
        }else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
