package nl.bigo.pythonparser;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 * A main class that demonstrates the use of the generated Python3
 * lexer and parser classes.
 */
public class Main {

    /**
     * An example how to use a listener that lists all method names from
     * a Python source string.
     *
     * @param source
     *         the Python source to list the methods from.
     */
    public static void listMethodNames(String source) {

        Python3Parser parser = new Builder.Parser(source).build();

        ParseTreeWalker.DEFAULT.walk(new Python3BaseListener() {

            // The grammar rule for a function definition looks like this:
            //
            //      funcdef
            //       : DEF NAME parameters ( '->' test )? ':' suite
            //       ;
            //
            @Override
            public void enterFuncdef(@NotNull Python3Parser.FuncdefContext ctx) {
                System.out.println("NAME=" + ctx.NAME().getText());
            }

        }, parser.file_input());
    }

    public static void main(String[] args) {

        String source = "def double(n):\n  return n + n\n";

        System.out.println(source);

        System.out.println(new Builder.Tree(source).toStringASCII());

        listMethodNames(source);
    }
}
