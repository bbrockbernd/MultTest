# Testing linearizability of popular concurrent frameworks with Lincheck

*By [Alexandru Dumtiriu](https://github.com/Alexandru-Dumitriu)
and [Bob Brockbernd](https://github.com/bbrockbernd)*.

# Current state of concurrency testing in Java

TODO

# What is Lincheck?

[Lincheck](https://github.com/JetBrains/lincheck) is a robust tool designed to facilitate concurrent testing and verification of thread-safe code. It seamlessly integrates with existing Java or Kotlin code, providing an easy-to-use framework for ensuring the correctness and reliability of concurrent algorithms and data structures.

At its core, Lincheck leverages state-of-the-art techniques to systematically explore the behaviour of concurrent executions. It can simulate various concurrency scenarios, such as multiple threads accessing shared resources concurrently or executing complex transactional operations. The tool meticulously monitors these executions, detecting potential race conditions, data races, and other concurrency bugs. It employs sophisticated algorithms to explore different interleavings of thread executions systematically, aiming to uncover subtle concurrency issues that may arise under specific scheduling conditions.

By utilizing the capabilities of Lincheck, we can conduct tests on some of the most commonly used Java concurrent libraries to confirm their linearizability through the model-checking functionality. If no problems are detected, we can confidently declare that the possibility of concurrency bugs occurring in practical situations is extremely low. However, if any issues are found, we aim to report them, so that they can be fixed in future versions of the libraries being tested.

## The bug detection technique

TODO

## Sources of nondeterminism

TODO

# Libraries Tested

TODO intro

## Guava

TODO

## Multiverse

TODO

## FastUtil

TODO

# Test Setup

For the tests we use a Windows 11 and a macOS machine with 16 and 64 gb of RAM. This allows us to test the selected data structures in complex scenarios, leveraging up to 16gb of JVM memory.

We use the latest version of all libraries tested and Lincheck as of 17th of April 2024.

# Results

TODO

# Conclusion

Our implementation is available on [GitHub](https://github.com/bbrockbernd/MultTest).