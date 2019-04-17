import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class Data implements Runnable {

    public long id;
    public String name;

    public String data;

    //Verhoogbare waarde om de hash aan te kunnen passen.
    private int nonce = 0;

    public Data(long id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public void increaseNonce() {
        Random r = new Random();
        nonce += r.nextInt(255);
    }

    public String hash() {
        return DigestUtils.sha256Hex(this.toString());
    }

    @Override
    public void run() {
        while(!hash().startsWith("0000")) {
            increaseNonce();
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id : ").append(id)
                .append(System.lineSeparator()).append("name : ").append(name)
                .append(System.lineSeparator()).append("nonce : ").append(nonce)
                .append(System.lineSeparator()).append("data : ").append(data);

        return sb.toString();
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
