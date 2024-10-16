package org.design.pattern.creational_patterns.singleton;

public class Theme {
    // 정적 필드
    // 자료형이 클래스 자체
    // 유일한 객체가 여기 저장됨.
    private static Theme instance;
    private String themeColor;

    // 외부에서 이 생성자를 쓰지 못하도록 private
    private Theme() {
        this.themeColor = "light"; // Default theme
    }

    public static Theme getInstance() {
        if (instance == null) {
            // 생성자 호출 코드거 정적 메서드에 들어있음.
            System.out.println(" 인스턴스 생성 ");
            instance = new Theme();
        }
        return instance;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }
}
