import java.awt.*;

class ColorBright extends Color {
    ColorBright(int r, int g, int b){
        super(r, g, b);
    }

    ColorBright(int rgb){
        super(rgb);
    }

    double getBrightness(){
        final double pr = .299;
        final double pg = .587;
        final double pb = .114;

        return Math.sqrt((pr * Math.pow(getRed(), 2)) + (pg * Math.pow(getGreen(), 2)) + (pb * Math.pow(getBlue(), 2)));
    }
}
