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
import javax.servlet.jsp.PageContext;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中文乱码问题
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        //文件上传
        //创建SmartUpload对象
        SmartUpload su = new SmartUpload();
        //初始化SmartUpLoad对象
        su.initialize(this.getServletConfig(), request, response);// 初始化
        String fileName = "";

        try {

            //允许上传文件类型
            su.setAllowedFilesList("jpg,gif,jpeg,png");
            //上传文件
            su.upload();
            //获取用户名参数值
            String userName = su.getRequest().getParameter("userName");
            //获取文件
            Files uploadFiles = su.getFiles();
            //获取文件名称（包括文件扩展名）
            fileName = uploadFiles.getFile(0).getFileExt();
            //文件命名（避免重复 使用id.文件名）
            fileName = userName+"."+fileName;

            //获取文件路径
            File f = new File(this.getServletContext().getRealPath("/upload/user"));
            if (!f.exists() && !f.isDirectory()) {
                f.mkdir();
            }
            //保存文件
            uploadFiles.getFile(0).saveAs("/upload/user/"+ fileName);

        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

        //获取用户名称、昵称、密码、再次确认的密码、性别、账号类别
        String userName = su.getRequest().getParameter("userName");
        String nickname = su.getRequest().getParameter("nickname");
        String passWord = su.getRequest().getParameter("passWord");
        String rePassWord = su.getRequest().getParameter("rePassWord");
        String sex = su.getRequest().getParameter("sex");
        String status = su.getRequest().getParameter("status");
        //实例化
        User u = new User(userName,nickname,passWord,sex,"0","0",status,fileName);
        System.out.println(u);

        UserService service = new UserServiceImpl();
        Boolean flag = service.addUser(u);
        if (flag) {
            response.sendRedirect(request.getContextPath()+"/userListServlet");
        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('新增用户失败！');");
            out.write("</script>");
            out.close();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
