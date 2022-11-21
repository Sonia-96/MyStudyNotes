package Tree;

public class ExpressionTree {

    private static class Node {
        char op;
        int val;
        Node left;
        Node right;

        public Node(char c, Node l, Node r) {
            op = c;
            left = l;
            right = r;
        }

        public Node (int v) {
            val = v;
            left = null;
            right = null;
        }

        public int eval() {
            if (left == null) {
                return val;
            }
            return switch(op) {
                case '+' -> left.eval() + right.eval();
                case '-' -> left.eval() - right.eval();
                case '*' -> left.eval() * right.eval();
                case '/' -> left.eval() / right.eval();
                default -> throw new UnsupportedOperationException("No such operation: " + op);
            };
        }
    }

    Node root;

    public ExpressionTree(Node r) {
        root = r;
    }

    public int eval() {
        return root.eval();
    }

    // TODO
    public void buildTree() {

    }

    public static void main(String[] args) {
        // (1 + 2) * 3
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node plus = new Node('+', n1, n2);
        Node times = new Node('*', plus, n3);
        ExpressionTree root = new ExpressionTree(times);
        System.out.println(root.eval());
    }
}
