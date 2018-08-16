package com.icstp.codeservlet;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icstp.util.VerifyCode;

/**
 *	讲师:张海清
 *	时间: 2018-4-1
 *	内容:写出图片
 */
public class CodeServlet extends HttpServlet {

	/**
	 * 写出图片:
	 * 1.获取输出流对象
	 * 2.将内存中的数据输出
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取输出流对象
		ServletOutputStream out = response.getOutputStream();// 向页面输出数据
		// 获取验证码图片
		VerifyCode vc = new VerifyCode();
		BufferedImage img = vc.getImage();
		
		// 将图片写出到指定输出流,
		// 要求输出流能将数据写入到页面中
		vc.output(img, out);
	}

}
