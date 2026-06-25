import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Reflection {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("Helper");
        Object instance = clazz.getDeclaredConstructor().newInstance();

        System.out.println("Loaded class: " + clazz.getName());

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("Method: " + method.getName() + " | Parameters: ");
            Parameter[] parameters = method.getParameters();
            if (parameters.length == 0) {
                System.out.print("none");
            } else {
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    System.out.print(parameter.getType().getSimpleName() + " " + parameter.getName());
                    if (i < parameters.length - 1) {
                        System.out.print(", ");
                    }
                }
            }
            System.out.println();
        }

        if (methods.length > 0) {
            Method methodToInvoke = methods[0];
            methodToInvoke.setAccessible(true);
            Object result;
            if (methodToInvoke.getParameterCount() == 0) {
                result = methodToInvoke.invoke(instance);
            } else if (methodToInvoke.getParameterCount() == 1) {
                result = methodToInvoke.invoke(instance, "World");
            } else {
                Object[] arguments = new Object[methodToInvoke.getParameterCount()];
                Class<?>[] paramTypes = methodToInvoke.getParameterTypes();
                for (int i = 0; i < paramTypes.length; i++) {
                    arguments[i] = getDefaultValue(paramTypes[i]);
                }
                result = methodToInvoke.invoke(instance, arguments);
            }
            System.out.println("Invoked method: " + methodToInvoke.getName() + " | Result: " + result);
        }
    }

    private static Object getDefaultValue(Class<?> type) {
        if (type == boolean.class)
            return false;
        if (type == byte.class)
            return (byte) 0;
        if (type == short.class)
            return (short) 0;
        if (type == int.class)
            return 0;
        if (type == long.class)
            return 0L;
        if (type == float.class)
            return 0f;
        if (type == double.class)
            return 0d;
        if (type == char.class)
            return '\u0000';
        return null;
    }
}

class Helper {
    private String greet(String name) {
        return "Hello, " + name + "!";
    }

    public int add(int a, int b) {
        return a + b;
    }

    void show() {
        System.out.println("show() method executed");
    }
}
