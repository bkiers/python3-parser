package nl.bigo.pythonparser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Python3LexerTest {

    private CommonTokenStream tokenize(String source) {

        // Make sure there is a line break at the end.
        source = source.trim() + "\n";

        Python3Lexer lexer = new Python3Lexer(new ANTLRInputStream(source));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Make sure all tokens are created and buffered.
        tokens.fill();

        return tokens;
    }

    @Test
    public void nameTest() {

        CommonTokenStream tokens = tokenize("foo   __bar__   MU_42   Σ");

        assertTrue(tokens.getTokens().size() >= 4);

        assertThat(tokens.get(0).getType(), is(Python3Lexer.NAME));
        assertThat(tokens.get(0).getText(), is("foo"));

        assertThat(tokens.get(1).getType(), is(Python3Lexer.NAME));
        assertThat(tokens.get(1).getText(), is("__bar__"));

        assertThat(tokens.get(2).getType(), is(Python3Lexer.NAME));
        assertThat(tokens.get(2).getText(), is("MU_42"));

        assertThat(tokens.get(3).getType(), is(Python3Lexer.NAME));
        assertThat(tokens.get(3).getText(), is("Σ"));
    }

    @Test
    public void stringLiteralTest() {

        CommonTokenStream tokens = tokenize("'''a'''    R'''b'''    r\"Σ\"    'mu \\n foo'");

        assertTrue(tokens.getTokens().size() >= 4);

        assertThat(tokens.get(0).getType(), is(Python3Lexer.STRING_LITERAL));
        assertThat(tokens.get(0).getText(), is("'''a'''"));

        assertThat(tokens.get(1).getType(), is(Python3Lexer.STRING_LITERAL));
        assertThat(tokens.get(1).getText(), is("R'''b'''"));

        assertThat(tokens.get(2).getType(), is(Python3Lexer.STRING_LITERAL));
        assertThat(tokens.get(2).getText(), is("r\"Σ\""));

        assertThat(tokens.get(3).getType(), is(Python3Lexer.STRING_LITERAL));
        assertThat(tokens.get(3).getText(), is("'mu \\n foo'"));
    }

    @Test
    public void bytesLiteralTest() {

        CommonTokenStream tokens = tokenize("b'''a'''    BR'''b'''    B\"123\"    Br'mu \\n foo'");

        assertTrue(tokens.getTokens().size() >= 4);

        assertThat(tokens.get(0).getType(), is(Python3Lexer.BYTES_LITERAL));
        assertThat(tokens.get(0).getText(), is("b'''a'''"));

        assertThat(tokens.get(1).getType(), is(Python3Lexer.BYTES_LITERAL));
        assertThat(tokens.get(1).getText(), is("BR'''b'''"));

        assertThat(tokens.get(2).getType(), is(Python3Lexer.BYTES_LITERAL));
        assertThat(tokens.get(2).getText(), is("B\"123\""));

        assertThat(tokens.get(3).getType(), is(Python3Lexer.BYTES_LITERAL));
        assertThat(tokens.get(3).getText(), is("Br'mu \\n foo'"));
    }

    @Test
    public void keywordsTest() {

        assertThat(tokenize("False").get(0).getType(), is(Python3Lexer.FALSE));
        assertThat(tokenize("None").get(0).getType(), is(Python3Lexer.NONE));
        assertThat(tokenize("True").get(0).getType(), is(Python3Lexer.TRUE));
        assertThat(tokenize("and").get(0).getType(), is(Python3Lexer.AND));
        assertThat(tokenize("as").get(0).getType(), is(Python3Lexer.AS));
        assertThat(tokenize("assert").get(0).getType(), is(Python3Lexer.ASSERT));
        assertThat(tokenize("break").get(0).getType(), is(Python3Lexer.BREAK));
        assertThat(tokenize("class").get(0).getType(), is(Python3Lexer.CLASS));
        assertThat(tokenize("continue").get(0).getType(), is(Python3Lexer.CONTINUE));
        assertThat(tokenize("def").get(0).getType(), is(Python3Lexer.DEF));
        assertThat(tokenize("del").get(0).getType(), is(Python3Lexer.DEL));
        assertThat(tokenize("elif").get(0).getType(), is(Python3Lexer.ELIF));
        assertThat(tokenize("else").get(0).getType(), is(Python3Lexer.ELSE));
        assertThat(tokenize("except").get(0).getType(), is(Python3Lexer.EXCEPT));
        assertThat(tokenize("finally").get(0).getType(), is(Python3Lexer.FINALLY));
        assertThat(tokenize("for").get(0).getType(), is(Python3Lexer.FOR));
        assertThat(tokenize("from").get(0).getType(), is(Python3Lexer.FROM));
        assertThat(tokenize("import").get(0).getType(), is(Python3Lexer.IMPORT));
        assertThat(tokenize("if").get(0).getType(), is(Python3Lexer.IF));
        assertThat(tokenize("in").get(0).getType(), is(Python3Lexer.IN));
        assertThat(tokenize("is").get(0).getType(), is(Python3Lexer.IS));
        assertThat(tokenize("lambda").get(0).getType(), is(Python3Lexer.LAMBDA));
        assertThat(tokenize("nonlocal").get(0).getType(), is(Python3Lexer.NONLOCAL));
        assertThat(tokenize("global").get(0).getType(), is(Python3Lexer.GLOBAL));
        assertThat(tokenize("pass").get(0).getType(), is(Python3Lexer.PASS));
        assertThat(tokenize("not").get(0).getType(), is(Python3Lexer.NOT));
        assertThat(tokenize("or").get(0).getType(), is(Python3Lexer.OR));
        assertThat(tokenize("raise").get(0).getType(), is(Python3Lexer.RAISE));
        assertThat(tokenize("return").get(0).getType(), is(Python3Lexer.RETURN));
        assertThat(tokenize("try").get(0).getType(), is(Python3Lexer.TRY));
        assertThat(tokenize("while").get(0).getType(), is(Python3Lexer.WHILE));
        assertThat(tokenize("with").get(0).getType(), is(Python3Lexer.WITH));
        assertThat(tokenize("yield").get(0).getType(), is(Python3Lexer.YIELD));
    }

    @Test
    public void integerLiteralTest() {

        assertThat(tokenize("7").get(0).getType(), is(Python3Lexer.DECIMAL_INTEGER));
        assertThat(tokenize("3").get(0).getType(), is(Python3Lexer.DECIMAL_INTEGER));
        assertThat(tokenize("2147483647").get(0).getType(), is(Python3Lexer.DECIMAL_INTEGER));
        assertThat(tokenize("79228162514264337593543950336").get(0).getType(), is(Python3Lexer.DECIMAL_INTEGER));

        assertThat(tokenize("0o377").get(0).getType(), is(Python3Lexer.OCT_INTEGER));
        assertThat(tokenize("0o177").get(0).getType(), is(Python3Lexer.OCT_INTEGER));

        assertThat(tokenize("0b100110111").get(0).getType(), is(Python3Lexer.BIN_INTEGER));

        assertThat(tokenize("0x100000000").get(0).getType(), is(Python3Lexer.HEX_INTEGER));
        assertThat(tokenize("0xdeadbeef").get(0).getType(), is(Python3Lexer.HEX_INTEGER));
    }

    @Test
    public void floatLiteralTest() {

        assertThat(tokenize("3.14").get(0).getType(), is(Python3Lexer.FLOAT_NUMBER));
        assertThat(tokenize("10.").get(0).getType(), is(Python3Lexer.FLOAT_NUMBER));
        assertThat(tokenize(".001").get(0).getType(), is(Python3Lexer.FLOAT_NUMBER));
        assertThat(tokenize("1e100").get(0).getType(), is(Python3Lexer.FLOAT_NUMBER));
        assertThat(tokenize("3.14e-10").get(0).getType(), is(Python3Lexer.FLOAT_NUMBER));
        assertThat(tokenize("0e0").get(0).getType(), is(Python3Lexer.FLOAT_NUMBER));
    }

    @Test
    public void imaginaryLiteralTest() {

        assertThat(tokenize("3.14j").get(0).getType(), is(Python3Lexer.IMAG_NUMBER));
        assertThat(tokenize("10.j").get(0).getType(), is(Python3Lexer.IMAG_NUMBER));
        assertThat(tokenize("10j").get(0).getType(), is(Python3Lexer.IMAG_NUMBER));
        assertThat(tokenize(".001j").get(0).getType(), is(Python3Lexer.IMAG_NUMBER));
        assertThat(tokenize("1e100j").get(0).getType(), is(Python3Lexer.IMAG_NUMBER));
        assertThat(tokenize("3.14e-10j").get(0).getType(), is(Python3Lexer.IMAG_NUMBER));
    }

    @Test
    public void getIndentationCountTest() {

        assertThat(Python3Lexer.getIndentationCount(""), is(0));
        assertThat(Python3Lexer.getIndentationCount(" "), is(1));
        assertThat(Python3Lexer.getIndentationCount("    "), is(4));
        assertThat(Python3Lexer.getIndentationCount("\t"), is(8));
        assertThat(Python3Lexer.getIndentationCount("      \t"), is(8));
        assertThat(Python3Lexer.getIndentationCount("\t  \t"), is(16));
    }

    @Test
    public void indentDedentTest() {

        CommonTokenStream tokens = tokenize("pass\n" + // PASS NEWLINE
                "  pass\n" +                           // INDENT PASS NEWLINE
                "    # skip empty lines!\n" +          // skipped
                "    pass\n" +                         // INDENT PASS NEWLINE
                "pass\n" +                             // DEDENT DEDENT PASS NEWLINE
                "        pass\n" +                     // INDENT PASS NEWLINE
                "\tpass\n" +                           // PASS NEWLINE ('\t' expands to 8 spaces!)
                "pass");                               // DEDENT PASS

        assertTrue(tokens.getTokens().size() >= 19);

        assertThat(tokens.get(0).getType(), is(Python3Parser.PASS));
        assertThat(tokens.get(1).getType(), is(Python3Parser.NEWLINE));
        assertThat(tokens.get(2).getType(), is(Python3Parser.INDENT));
        assertThat(tokens.get(3).getType(), is(Python3Parser.PASS));
        assertThat(tokens.get(4).getType(), is(Python3Parser.NEWLINE));
        assertThat(tokens.get(5).getType(), is(Python3Parser.INDENT));
        assertThat(tokens.get(6).getType(), is(Python3Parser.PASS));
        assertThat(tokens.get(7).getType(), is(Python3Parser.NEWLINE));
        assertThat(tokens.get(8).getType(), is(Python3Parser.DEDENT));
        assertThat(tokens.get(9).getType(), is(Python3Parser.DEDENT));
        assertThat(tokens.get(10).getType(), is(Python3Parser.PASS));
        assertThat(tokens.get(11).getType(), is(Python3Parser.NEWLINE));
        assertThat(tokens.get(12).getType(), is(Python3Parser.INDENT));
        assertThat(tokens.get(13).getType(), is(Python3Parser.PASS));
        assertThat(tokens.get(14).getType(), is(Python3Parser.NEWLINE));
        assertThat(tokens.get(15).getType(), is(Python3Parser.PASS));
        assertThat(tokens.get(16).getType(), is(Python3Parser.NEWLINE));
        assertThat(tokens.get(17).getType(), is(Python3Parser.DEDENT));
        assertThat(tokens.get(18).getType(), is(Python3Parser.PASS));
    }

    @Test
    public void operatorDelimiterTest() {

        CommonTokenStream tokens = tokenize(".    ...    *    (    )    ,    :" +
                "    ;    **    =    [    ]    |    ^    &    <<    >>    +" +
                "    -    /    %    //    ~    {    }    <    >    ==    >=" +
                "    <=    <>    !=    @    ->    +=    -=    *=    /=    %=" +
                "    &=    |=    ^=    <<=    >>=    **=    //=  "
        );

        assertTrue(tokens.getTokens().size() >= 46);

        assertThat(tokens.get(0).getType(), is(Python3Parser.DOT));
        assertThat(tokens.get(1).getType(), is(Python3Parser.ELLIPSIS));
        assertThat(tokens.get(2).getType(), is(Python3Parser.STAR));
        assertThat(tokens.get(3).getType(), is(Python3Parser.OPEN_PAREN));
        assertThat(tokens.get(4).getType(), is(Python3Parser.CLOSE_PAREN));
        assertThat(tokens.get(5).getType(), is(Python3Parser.COMMA));
        assertThat(tokens.get(6).getType(), is(Python3Parser.COLON));
        assertThat(tokens.get(7).getType(), is(Python3Parser.SEMI_COLON));
        assertThat(tokens.get(8).getType(), is(Python3Parser.POWER));
        assertThat(tokens.get(9).getType(), is(Python3Parser.ASSIGN));
        assertThat(tokens.get(10).getType(), is(Python3Parser.OPEN_BRACK));
        assertThat(tokens.get(11).getType(), is(Python3Parser.CLOSE_BRACK));
        assertThat(tokens.get(12).getType(), is(Python3Parser.OR_OP));
        assertThat(tokens.get(13).getType(), is(Python3Parser.XOR));
        assertThat(tokens.get(14).getType(), is(Python3Parser.AND_OP));
        assertThat(tokens.get(15).getType(), is(Python3Parser.LEFT_SHIFT));
        assertThat(tokens.get(16).getType(), is(Python3Parser.RIGHT_SHIFT));
        assertThat(tokens.get(17).getType(), is(Python3Parser.ADD));
        assertThat(tokens.get(18).getType(), is(Python3Parser.MINUS));
        assertThat(tokens.get(19).getType(), is(Python3Parser.DIV));
        assertThat(tokens.get(20).getType(), is(Python3Parser.MOD));
        assertThat(tokens.get(21).getType(), is(Python3Parser.IDIV));
        assertThat(tokens.get(22).getType(), is(Python3Parser.NOT_OP));
        assertThat(tokens.get(23).getType(), is(Python3Parser.OPEN_BRACE));
        assertThat(tokens.get(24).getType(), is(Python3Parser.CLOSE_BRACE));
        assertThat(tokens.get(25).getType(), is(Python3Parser.LESS_THAN));
        assertThat(tokens.get(26).getType(), is(Python3Parser.GREATER_THAN));
        assertThat(tokens.get(27).getType(), is(Python3Parser.EQUALS));
        assertThat(tokens.get(28).getType(), is(Python3Parser.GT_EQ));
        assertThat(tokens.get(29).getType(), is(Python3Parser.LT_EQ));
        assertThat(tokens.get(30).getType(), is(Python3Parser.NOT_EQ_1));
        assertThat(tokens.get(31).getType(), is(Python3Parser.NOT_EQ_2));
        assertThat(tokens.get(32).getType(), is(Python3Parser.AT));
        assertThat(tokens.get(33).getType(), is(Python3Parser.ARROW));
        assertThat(tokens.get(34).getType(), is(Python3Parser.ADD_ASSIGN));
        assertThat(tokens.get(35).getType(), is(Python3Parser.SUB_ASSIGN));
        assertThat(tokens.get(36).getType(), is(Python3Parser.MULT_ASSIGN));
        assertThat(tokens.get(37).getType(), is(Python3Parser.DIV_ASSIGN));
        assertThat(tokens.get(38).getType(), is(Python3Parser.MOD_ASSIGN));
        assertThat(tokens.get(39).getType(), is(Python3Parser.AND_ASSIGN));
        assertThat(tokens.get(40).getType(), is(Python3Parser.OR_ASSIGN));
        assertThat(tokens.get(41).getType(), is(Python3Parser.XOR_ASSIGN));
        assertThat(tokens.get(42).getType(), is(Python3Parser.LEFT_SHIFT_ASSIGN));
        assertThat(tokens.get(43).getType(), is(Python3Parser.RIGHT_SHIFT_ASSIGN));
        assertThat(tokens.get(44).getType(), is(Python3Parser.POWER_ASSIGN));
        assertThat(tokens.get(45).getType(), is(Python3Parser.IDIV_ASSIGN));
    }

    @Test
    public void unknownCharTest() {

        CommonTokenStream tokens = tokenize("$    ?    `");

        assertTrue(tokens.getTokens().size() >= 3);

        assertThat(tokens.get(0).getType(), is(Python3Parser.UNKNOWN_CHAR));
        assertThat(tokens.get(1).getType(), is(Python3Parser.UNKNOWN_CHAR));
        assertThat(tokens.get(2).getType(), is(Python3Parser.UNKNOWN_CHAR));
    }
}
