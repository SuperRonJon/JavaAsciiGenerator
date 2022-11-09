import java.awt.*;

class ColorBright extends Color {
    ColorBright(int r, int g, int b){
        super(r, g, b);
    }

    ColorBright(int rgb){
        super(rgb);
    }

    /*
        Determines the perceived brightness value of each pixel.
        Formula sources: https://stackoverflow.com/questions/596216/formula-to-determine-perceived-brightness-of-rgb-color
        Stack overflow page sourced from: https://alienryderflex.com/hsp.html
    */
    double getBrightness(){
        final double pr = .299;
        final double pg = .587;
        final double pb = .114;

        return Math.sqrt((pr * Math.pow(getRed(), 2)) + (pg * Math.pow(getGreen(), 2)) + (pb * Math.pow(getBlue(), 2)));
    }
}
