import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    List<Signal> calculateHandshake(int number) {
        // number to binary
        List<Signal> handshake = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while(number > 0) {
            if (number % 2 == 0) {
                sb.append("0");
                number /= 2;
            }
            else if (number % 2 == 1){
                sb.append("1");
                number--;
                number /= 2;
            }
        }
        int len = sb.length();
        if(len == 0){
            return Collections.emptyList();
        }
        if(sb.charAt(0) == '1'){
            handshake.add(Signal.WINK);
        }
        if(len > 1 && sb.charAt(1) == '1'){
            handshake.add(Signal.DOUBLE_BLINK);
        }
        if(len > 2 && sb.charAt(2) == '1'){
            handshake.add(Signal.CLOSE_YOUR_EYES);
        }
        if(len > 3 && sb.charAt(3) == '1'){
            handshake.add(Signal.JUMP);
        }
        if(len > 4 && sb.charAt(4) == '1'){
            Collections.reverse(handshake);
        }
        return handshake;
    }

}
