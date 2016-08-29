import static org.fusesource.jansi.Ansi.*;

/**
 * Created by ecanmir on 22.08.2016.
 */
public class ColorfulCLI {
	public static final String ANSI_RESET  = "\u001B[0m";
	public static final String ANSI_BLACK  = "\u001B[30m";
	public static final String ANSI_RED    = "\u001B[31m";
	public static final String ANSI_GREEN  = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE   = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN   = "\u001B[36m";
	public static final String ANSI_WHITE  = "\u001B[37m";

	public static final String ANSI_COLOR_UNICODE = "\u001B";
	public static final String ANSI_RESET_CODE    = ANSI_COLOR_UNICODE + "[0m";
	public static final int    ANSI_BLUE_VALUE    = 34;
	public static final int    ANSI_RESET_VALUE   = 0;

	public static void main(String[] args) {
		//		StringBuilder sb = new StringBuilder();
		//		for (int i = 0, c = 30; i < 1000; i++) {
		//			StringBuilder colorfulChar = new StringBuilder();
		//			if (c > 45) {
		//				c = 30;
		//			}
		//			if (i % 819 == 0) {
		//				sb.append("\n");
		//			}
		//
		//			colorfulChar.append(ANSI_COLOR_UNICODE).append("[").append(c).append("m").append("#").append(
		//					ANSI_COLOR_UNICODE).append("[").append(ANSI_RESET_VALUE).append("m");
		//			//			System.out.print(colorfulChar.toString());
		//			sb.append(colorfulChar);
		//			c++;
		//			System.out.println(colorfulChar.toString());
		//		}
		//
		//		//		StringBuilder sb1 =
		//		//				new StringBuilder(ANSI_COLOR_UNICODE).append("[").append(ANSI_BLUE_VALUE).append("m").append("#")
		//		//						.append(ANSI_COLOR_UNICODE).append("[").append(ANSI_RESET_VALUE).append("m");
		//		//		System.out.println(sb1.toString());
		//		//		System.out.print(ANSI_BLUE + "This text is blue!" + ANSI_RESET);
		//		//
		//		//		System.out.print(ANSI_CYAN + "This text is cyan!" + ANSI_RESET);
		//		//
		//		//		System.out.print(ANSI_GREEN + "This text is green!" + ANSI_RESET);
		//		//
		//		//		System.out.print(ANSI_RED + "This text is red!" + ANSI_RESET);
		//		//		System.out.println("test....");
		//		//
		//		//		System.out.println((char) 27 + "[2J");
		//
		//		System.out.println(sb.toString());
		org.fusesource.jansi.AnsiConsole.systemInstall();
		System.out.println(ansi().eraseScreen().fg(org.fusesource.jansi.Ansi.Color.RED).a("Hello").newline()
				                   .fg(org.fusesource.jansi.Ansi.Color.GREEN).a("World").reset());
		org.fusesource.jansi.AnsiConsole.systemUninstall();

	}
}
