package shop.mtcoding.marketkurly._core.utils;

public class Script {

    // 뒤로가기(일반적으로 경고창과 함께 사용)
    public static String back(String msg) {
        System.out.println("back 스크립트 발동");
        StringBuilder sb = new StringBuilder();

        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }

    // 그냥 이동
    public static String href(String url, String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("location.href='" + url + "';");
        sb.append("</script>");

        return sb.toString();
    }

    // 경고창과 함께 이동
    public static String href(String url) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("location.href='" + url + "';");
        sb.append("</script>");
        return sb.toString();
    }
}
