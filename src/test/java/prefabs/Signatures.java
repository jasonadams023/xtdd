package prefabs;

import projectStructure.functionObjects.signature.Signature;

public class Signatures {
    public static Signature voidNullSignature = new Signature("function", "void", null);
    public static Signature intIntSignature = new Signature("function", "Integer", InputTypes.singleIntegerInputs);
}
