package operationPackage;
import java.io.Serializable;
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    private int op1;
    private int op2;
    private char operateur;

    public Operation(int op1, char operateur, int op2) {
        this.op1 = op1;
        this.op2 = op2;
        this.operateur = operateur;
    }

    public int getOp1() { return op1; }
    public int getOp2() { return op2; }
    public char getOperateur() { return operateur; }
}
