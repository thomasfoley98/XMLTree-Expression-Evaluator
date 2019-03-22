import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
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
    private static NaturalNumber evaluate(XMLTree exp) {
        NaturalNumber total = new NaturalNumber2(0);
        NaturalNumber tempTotal = new NaturalNumber2(0);
        NaturalNumber tempRightPlus = new NaturalNumber2(0);
        NaturalNumber tempLeftPlus = new NaturalNumber2(0);
        NaturalNumber tempRightMinus = new NaturalNumber2(0);
        NaturalNumber tempLeftMinus = new NaturalNumber2(0);
        NaturalNumber tempRightTimes = new NaturalNumber2(0);
        NaturalNumber tempLeftTimes = new NaturalNumber2(0);
        NaturalNumber tempRightDivide = new NaturalNumber2(0);
        NaturalNumber tempLeftDivide = new NaturalNumber2(0);

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
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    NaturalNumber transition2 = new NaturalNumber2(leftInt);
                    tempRightPlus.add(transition);
                    tempLeftPlus.add(transition2);
                    tempRightPlus.add(tempLeftPlus);
                    total.copyFrom(tempRightPlus);

                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    tempRightPlus.add(transition);
                    total = evaluate(exp.child(0));
                    total.add(tempRightPlus);
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    NaturalNumber transition = new NaturalNumber2(leftInt);
                    tempLeftPlus.add(transition);
                    total = evaluate(exp.child(1));
                    total.add(tempLeftPlus);
                }

            }
            if (exp.label().equals("minus")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    NaturalNumber transition2 = new NaturalNumber2(leftInt);
                    tempRightMinus.add(transition);
                    tempLeftMinus.add(transition2);
                    tempLeftMinus.subtract(tempRightMinus);
                    total.copyFrom(tempLeftMinus);

                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    tempRightMinus.add(transition);
                    total = evaluate(exp.child(0));
                    total.subtract(tempRightMinus);
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    NaturalNumber transition = new NaturalNumber2(leftInt);
                    tempLeftMinus.add(transition);
                    total = evaluate(exp.child(1));
                    total.subtract(tempLeftMinus);
                }

            }
            if (exp.label().equals("times")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    NaturalNumber transition2 = new NaturalNumber2(leftInt);
                    tempRightTimes.add(transition);
                    tempLeftTimes.add(transition2);
                    tempLeftTimes.multiply(tempRightTimes);
                    total.copyFrom(tempLeftTimes);

                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    tempRightTimes.add(transition);
                    total = evaluate(exp.child(0));
                    total.multiply(tempRightTimes);
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    NaturalNumber transition = new NaturalNumber2(leftInt);
                    tempLeftTimes.add(transition);
                    total = evaluate(exp.child(1));
                    total.multiply(tempLeftTimes);
                }

            }
            if (exp.label().equals("divide")) {
                if (exp.child(0).label().equals("number")
                        && exp.child(1).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    right = exp.child(1).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    NaturalNumber transition2 = new NaturalNumber2(leftInt);

                    tempRightDivide.add(transition);
                    tempLeftDivide.add(transition2);
                    tempLeftDivide.divide(tempRightDivide);
                    total.copyFrom(tempLeftDivide);

                } else if (exp.child(0).isTag()
                        && exp.child(1).label().equals("number")) {
                    right = exp.child(1).attributeValue("value");
                    rightInt = Integer.parseInt(right);
                    NaturalNumber transition = new NaturalNumber2(rightInt);
                    tempRightDivide.add(transition);
                    total = evaluate(exp.child(0));
                    total.divide(tempRightDivide);
                } else if (exp.child(1).isTag()
                        && exp.child(0).label().equals("number")) {
                    left = exp.child(0).attributeValue("value");
                    leftInt = Integer.parseInt(left);
                    NaturalNumber transition = new NaturalNumber2(leftInt);
                    tempLeftDivide.add(transition);
                    total = evaluate(exp.child(1));
                    total.divide(tempLeftDivide);
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
