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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateUserListServlet")
public class UpdateUserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

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
            //获取用户id参数信息并转化为Intege类型
            int UserId = Integer.valueOf(request.getParameter("userId"));
            //获取文件
            Files uploadFiles = su.getFiles();
            //获取文件名称（包括文件扩展名）
            fileName = uploadFiles.getFile(0).getFileExt();
            //获取文件扩展名
            String fileNameExt = uploadFiles.getFile(0).getFileExt();

            if("".equals(fileName)) {
                //文件扩展名不等
                System.out.println("1kong");
                //创建商品服务层对象 查询该商品后传到前台
                UserService service = new UserServiceImpl();
                User u = service.findUserByUserId(UserId);
                //文件命名为用户头像
                fileName = u.getUser_photo();
            }else {
                System.out.println("no kong");
                fileName =  UserId+"."+fileNameExt;
                File f = new File(this.getServletContext().getRealPath("/upload/user"));
                if (!f.exists() && !f.isDirectory()) {
                    f.mkdir();
                }
             //保存文件
                uploadFiles.getFile(0).saveAs("/upload/user/"+ fileName);
            }



        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

        //获取用户id、昵称、用户账号、密码、性别、VIP状态和身份为用户参数值
        int userId = Integer.valueOf(request.getParameter("userId"));
        String nickname = su.getRequest().getParameter("nickName");
        String username = su.getRequest().getParameter("name");
        String passWord = su.getRequest().getParameter("passWord");
        String sex = su.getRequest().getParameter("sex");
        String vip="0";
        String viptime="0";
        String status="1";
        //实例化user对象
        User user = new User(username,nickname,passWord,sex,vip,viptime,status,fileName);
        user.setUser_id(userId);
        System.out.println(user);
        //创建用户服务层对象 查询该用户后传到前台
        UserService service = new UserServiceImpl();
        service.updateUserById(user);
        //前端响应
        PrintWriter out = response.getWriter();
        //弹出窗口
        out.write("<script>");
        out.write("alert('更新成功！');");
        out.write("location.href='/HOMEECMS/userListServlet'");
        out.write("</script>");
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
