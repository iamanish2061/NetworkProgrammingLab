package lab7;

import java.io.IOException;
import java.net.SocketOption;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Solution {

    public static void main(String[] args){
        listAllSocket();
        fillingAndDraining();
    }

//    Write a program to list all supported socket options for the different types of network channels.
    private static void listAllSocket(){
        try {
            // 1. SocketChannel (TCP Client)
            SocketChannel socketChannel = SocketChannel.open();
            printOptions("SocketChannel", socketChannel.supportedOptions());
            socketChannel.close();

            // 2. ServerSocketChannel (TCP Server)
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            printOptions("ServerSocketChannel", serverSocketChannel.supportedOptions());
            serverSocketChannel.close();

            // 3. DatagramChannel (UDP)
            DatagramChannel datagramChannel = DatagramChannel.open();
            printOptions("DatagramChannel", datagramChannel.supportedOptions());
            datagramChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void printOptions(String channelType, Set<SocketOption<?>> options) {
        System.out.println("Supported options for " + channelType + ":");
        for (SocketOption<?> option : options) {
            System.out.println("  - " + option.name());
        }
        System.out.println();
    }

//    Write a program to implement the concept on filling and draining buffer, duplicating buffer, slicing buffer, compact buffer.
    public static void fillingAndDraining() {
        // 1. Create a buffer with capacity 10
        CharBuffer buffer = CharBuffer.allocate(10);
        System.out.println("Initial State...");
        printStats(buffer);

        // 2. FILLING (put)
        System.out.println("\nFilling Buffer ('H', 'e', 'l', 'l', 'o')");
        buffer.put("Hello");
        printStats(buffer);

        // 3. DRAINING Preparation (flip)
        // Flip switches from writing mode to reading mode
        System.out.println("\nFlipping (Prepare to Drain)");
        buffer.flip();
        printStats(buffer);

        // DRAINING (get)
        System.out.println("Draining first 2 chars: " + buffer.get() + buffer.get());
        printStats(buffer);

        // 4. DUPLICATING
        // Creates a new buffer that shares content but has independent position/limit
        System.out.println("\nDuplicating...");
        CharBuffer duplicate = buffer.duplicate();
        System.out.println("Duplicate Buffer Stats: " + duplicate);
        System.out.println("Shared content check: Changing original affects duplicate.");

        // 5. SLICING
        // Creates a new buffer starting at current position
        System.out.println("\nSlicing...");
        CharBuffer slice = buffer.slice();
        System.out.println("Slice content: " + slice.toString()); // Should be "llo"

        // 6. COMPACTING
        // Moves remaining data (unread) to the beginning for more writing
        System.out.println("\nCompacting...");
        buffer.compact(); // Moves "llo" to index 0, 1, 2. Position becomes 3.
        printStats(buffer);
        // Verify by filling more
        buffer.put(" World");
        buffer.flip();
        System.out.println("Content after compact + write: " + buffer.toString());
    }
    private static void printStats(CharBuffer cb) {
        System.out.println("Pos: " + cb.position() + ", Limit: " + cb.limit() + ", Cap: " + cb.capacity());
    }
}


