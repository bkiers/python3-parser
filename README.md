# Python 3 parser &nbsp; [![Build Status](https://travis-ci.org/bkiers/python3-parser.png)](https://travis-ci.org/bkiers/python3-parser)

An ANTLR4 grammar for Python 3 based on version 3.3.5 of 
[The Python Language Reference](https://docs.python.org/3.3/reference/grammar.html).

The unit tests consist of parsing all Python source files from 
Python 3's [standard library](http://hg.python.org/cpython/file/default/Lib/) 
which will take about 20 to 30 seconds to complete.    

## Install

To install this library, do the following:

```bash
git clone https://github.com/bkiers/python3-parser
cd python3-parser
mvn clean install
```

## Example

Some examples are given in [this Main class](https://github.com/bkiers/python3-parser/blob/master/src/main/java/nl/bigo/pythonparser/Main.java).

