package function;

class FunctionFactory {
    Function newFunction(String name) {
        return new Function(name);
    }
}
