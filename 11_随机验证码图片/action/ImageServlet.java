package action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageServlet extends HttpServlet {
	private int height = 40; // 图片的宽度
	private int width = 100; // 图片的高度
	private static int InterupetLines = 80; // 干扰线的条数
	private static int randomcode = 4; // 产生随机数的个数
	String[] Codes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
			"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
	};
	protected void service(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// 设定输出回应类型为图片
		HttpSession session = request.getSession();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		// 产生随机图片
		String code = getRandomImage(response);
		session.setAttribute("yzm", code);
	}
	// 产生随图片
	public String getRandomImage(HttpServletResponse response) {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(getRandColor(250, 200));//设置填充颜色
		g.fillRect(0, 0, width, height);//画出图片范围;参1和参2:图片边框.参3和参4:图片的大小(包括边框)
		// 绘制干扰线
		drawLines(g);
		//获得验证码
		String code = getRandomCode(g);
		g.dispose();//释放此图形的上下文以及它使用的所有系统资源
		//打印图片
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			return code;//用于比较验证码是否匹配
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}

	// 产生定义随机数
	public  String getRandomCode(Graphics g) {
		// 定义一个产生随机数的数组
		g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
		StringBuilder str = new StringBuilder("");// 创立一个装在随机字符的字符串；
		//Math.random()获得0.0到1.0的随机数,如0.3444906740412891
		//Math.floor(参数);返回最大的double值，该值小于等于参数，并等于某个整数
		for (int i = 0; i < randomcode; i++) {
			int random = (int) Math.floor(Math.random() * 62);
			g.setColor(getRandColor(100, 0));//设置图片颜色,所有后续图形操作均使用这个指定的颜色。
			g.drawString(Codes[random], i*18+15, 30);//设置验证码在图片中的位置(以最左侧字符的基线为准)
			str.append(Codes[random]);
		}
		return str.toString();
	}
	// 具体如何绘制干扰线
	public void drawLines(Graphics g) {
		g.setColor(getRandColor(200,180));//设置随机颜色
		for (int i = 0; i < InterupetLines; i++) {
			Random r = new Random();
			int x1 = r.nextInt(width);//返回0到width之间的随机数
			int y1 = r.nextInt(height);//返回0到height之间的随机数
			int x2 = r.nextInt(width);
			int y2 = r.nextInt(height);
			//使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线
			g.drawLine(x1, y1, x2, y2);
		}
	}
	// 生成随机的颜色
	 Color getRandColor(int bc, int fc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
}
