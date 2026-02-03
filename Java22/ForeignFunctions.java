import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import static java.lang.foreign.FunctionDescriptor.of;

public class ForeignFunctions {
    static void main(String[] args) throws Throwable {

        System.out.println("Using native linker to look up symbols in commonly used system libraries");
        SymbolLookup stdlib = Linker.nativeLinker().defaultLookup();

        System.out.println("Retrieving 'strlen' method handle");
        MethodHandle strlen = Linker.nativeLinker()
            .downcallHandle(
                    stdlib.find("strlen").orElseThrow(),
                    of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS));

        try (Arena offHeap = Arena.ofConfined()) {
            final String TARGET_STRING = "A cool test string";

            System.out.printf("Creating a memory segment from target string: '%s'%n", TARGET_STRING);
            MemorySegment memorySegment = offHeap.allocateFrom(TARGET_STRING);

            System.out.println("Invoking 'strlen' on the memory segment");
            long len = (long) strlen.invoke(memorySegment);

            System.out.println("Returned: " + len);
        }
    }
}