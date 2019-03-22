import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Tommy Foley
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
    }

    /*
     * Put the tag checks before the recursive call That way the numbers are
     * saved and can be used when the call resumes
     */
    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        int total = 0;

        String left = "";
        String right = "";
        int leftInt = 0;
        int rightInt = 0;
        if (exp.isTag()) {
            if (exp.label().equals("plus")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    total = leftInt + rightInt;
                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    total = evaluate(exp.child(0));
                    total = total + rightInt;
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    total = evaluate(exp.child(1));
                    total = leftInt + total;
                }

            }
            if (exp.label().equals("minus")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    total = leftInt - rightInt;
                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    total = evaluate(exp.child(0));
                    total = total - rightInt;
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    total = evaluate(exp.child(1));
                    total = leftInt - total;

                }

            }
            if (exp.label().equals("divide")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    total = leftInt / rightInt;
                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    total = evaluate(exp.child(0));
                    total = total / rightInt;
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    total = evaluate(exp.child(1));
                    total = leftInt / total;
                }

            }
            if (exp.label().equals("times")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    total = leftInt * rightInt;
                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    total = evaluate(exp.child(0));
                    total = total * rightInt;
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    total = evaluate(exp.child(1));
                    total = leftInt * total;
                }

            }

        }
        return total;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
