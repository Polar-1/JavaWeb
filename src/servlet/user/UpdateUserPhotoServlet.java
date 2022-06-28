package servlet.user;

import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import entity.Product;
import entity.User;
import service.ProductService;
import service.UserService;
import service.impl.ProductServiceImpl;
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

@WebServlet("/updateUserPhotoServlet")
public class UpdateUserPhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");
        if (user != null) {

            //文件上传
            //创建SmartUpload对象
            SmartUpload su = new SmartUpload();
            //初始化SmartUpLoad对象
            su.initialize(this.getServletConfig(), request, response);// 初始化
            String fileName = "";
            int userId = user.getUser_id();

            try {
                //允许上传文件类型
                su.setAllowedFilesList("jpg,gif,jpeg,png");
                //上传文件
                su.upload();
                //获取文件
                Files uploadFiles = su.getFiles();
                //获取文件名称（包括文件扩展名）
                fileName = uploadFiles.getFile(0).getFileExt();
                //文件命名（避免重复 使用id.文件名）
                fileName = userId + "." + fileName;
                //获取文件路径
                File f = new File(this.getServletContext().getRealPath("/upload/user"));
                if (!f.exists() && !f.isDirectory()) {
                    f.mkdir();
                }
                //保存文件
                uploadFiles.getFile(0).saveAs("/upload/user/" + fileName);

            } catch (SmartUploadException e) {
                e.printStackTrace();
            }

            //创建用户服务层对象 查询该用户后传到前台
            //实例化service层中UserService对象
            UserService service = new UserServiceImpl();
            service.updatePhotoById(userId,fileName);

            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('更新成功！');");
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
