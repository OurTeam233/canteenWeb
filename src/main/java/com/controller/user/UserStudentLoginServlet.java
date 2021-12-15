package com.controller.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mapper.UserMapper;
import com.pojo.Result;
import com.pojo.Student;
import com.pojo.StudentInfo;
import com.service.StudentService;
import com.service.UserService;
import com.util.JwtUtil;
import com.util.SqlSessionFactoryUtils;
import com.util.WxRequestUtil;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;

/**
 * @author tang
 */
@WebServlet("/Login/Student")
public class UserStudentLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(UserStudentLoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        //处理
        // 尝试获取参数
        String code = request.getParameter("code");
        // 获取请求体参数
        BufferedReader reader = request.getReader();
        String postBody = IOUtils.toString(reader);
        // 将参数转换为JSON对象
        JSONObject jsonUserInfo = JSON.parseObject(postBody).getJSONObject("userInfo");
        String className = jsonUserInfo.getString("class");
        String department = jsonUserInfo.getString("department");
        String sequence = jsonUserInfo.getString("sequence");
        // 构造查询user服务对象
        UserService userService = new UserService();
        Result result = userService.selectUserStudent(sequence, department, className);
        // TODO 待修改，当前是永远成功
        result.setSuccess(true);
        if (result.isSuccess()) {
            // 初始化
            int studentId;
            // 登录成功, 获取openid
            String openId = WxRequestUtil.getOpenId(code);
            // 构造查询student服务对象
            StudentService studentService = new StudentService();
            Student studentBySequence = studentService.selectStudentBySequence(sequence);
            if (studentBySequence == null) {
                // 创建学生信息，并将学生信息插入数据库
                // 封装student数据
                Student student = JSON.parseObject(JSON.toJSONString(jsonUserInfo), Student.class);
                student.setOpenid(openId);
                // TODO openId在服务器不能获取，之后再改
                // 将openid等各种数据存入数据库
                studentId = studentService.insertStudent(student);
            } else {
                // 查询到学生的studentId
                studentId = studentBySequence.getId();
            }
            // 生成token
            // 获取ua和ip
            String requestHeader = request.getHeader("User-Agent");
            String remoteAddr = request.getRemoteAddr();
            String token = JwtUtil.generateToken(studentId + "", "1", requestHeader, remoteAddr);
            // 构造结果集
            result.setOpenId(openId);
            result.setToken(token);
        }
        //输出
        out.print(JSON.toJSONString(result));
    }

}