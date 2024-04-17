# Testing linearizability with Lincheck

*By [Alexandru Dumtiriu](https://github.com/Alexandru-Dumitriu)
and [Bob Brockbernd](https://github.com/bbrockbernd)*.

# Introduction

We leverage a novel tool, Lincheck, to test popular libraries that use concurrency. Our experiments on certain data structures impelmented in these libraries have led to several interesting findings, both related to the tested frameworks and Lincheck itself. Our main objective in this project is to identify at least one bug in the tested libraries and report it to the developers responsible for maintaining them.

## Linearizability

In their paper ["Linearizability: a correctness condition for concurrent objects"](https://cs.brown.edu/~mph/HerlihyW90/p463-herlihy.pdf), Herlihy and Wing introduce a key criterion for evaluating the correctness of concurrent object implementations. The basic idea is to ensure that although an implementation may not demonstrate every possible interleaving permitted by linearizability, every realized interleaving must comply with the linearizability principle [1]. This means that all operations, regardless of their concurrency, must seem atomic or instantaneous from an external perspective. This strict requirement guarantees the integrity and dependability of concurrent systems, preventing unexpected behaviors or inconsistencies in concurrent operations.


## Sources of non-determinism

Concurrency in programming can be challenging due to non-determinism, especially delays, which can cause errors. Testing data structures can be even more complicated, making it difficult to identify bugs. Stress testing is a common approach, but it is often unable to detect elusive bugs caused by non-determinism. In addition, when different threads access the same data concurrently, an operation that spans multiple instructions in one thread can result in erroneous outcomes if it lacks atomicity. While tools like Lincheck can provide insights into the proximity to linearizability, they cannot guarantee it. Nevertheless, a successful test can boost confidence in the concurrency model's fidelity under real-world scenarios.

# What is Lincheck?

[Lincheck](https://github.com/JetBrains/lincheck) is a robust tool designed to facilitate concurrent testing and verification of thread-safe code. It seamlessly integrates with existing Java or Kotlin code, providing an easy-to-use framework for ensuring the correctness and reliability of concurrent algorithms and data structures.

At its core, Lincheck leverages state-of-the-art techniques to systematically explore the behaviour of concurrent executions. It can simulate various concurrency scenarios, such as multiple threads accessing shared resources concurrently or executing complex transactional operations. The tool meticulously monitors these executions, detecting potential race conditions, data races, and other concurrency bugs. It employs sophisticated algorithms to explore different interleavings of thread executions systematically, aiming to uncover subtle concurrency issues that may arise under specific scheduling conditions.

By utilizing the capabilities of Lincheck, we can conduct tests on some of the most commonly used Java concurrent libraries to confirm their linearizability through the model-checking functionality. If no problems are detected, we can confidently declare that the possibility of concurrency bugs occurring in practical situations is extremely low. However, if any issues are found, we aim to report them, so that they can be fixed in future versions of the libraries being tested.

## The bug detection technique

TODO

## Test Setup

For the tests we use a Windows 11 and a macOS machine with 16 and 64 gb of RAM. This allows us to test the selected data structures in complex scenarios, leveraging up to 16gb of JVM memory.

We use the latest version of all libraries tested and Lincheck as of 17th of April 2024.


## Bug Injection Test

TODO

# Related Work

TODO

# Results

TODO intro

## Guava

TODO

## Multiverse

TODO

## FastUtil

TODO

## Agrona

TODO

# Conclusion

TODO

# References

1. Herlihy, M.P., Wing, J.M.: Linearizability: a correctness condition for concurrent objects. ACM Trans. Program. Lang. Syst. (TOPLAS) 12(3), 463–492 (1990)
2. Koval, N., Fedorov, A., Sokolova, M., Tsitelov, D., Alistarh, D. (2023). Lincheck: A Practical Framework for Testing Concurrent Data Structures on JVM. In: Enea, C., Lal, A. (eds) Computer Aided Verification. CAV 2023. Lecture Notes in Computer Science, vol 13964. Springer, Cham. https://doi.org/10.1007/978-3-031-37706-8_8
3. The Java Concurrency Stress tests. https://openjdk.java.net/projects/code-tools/jcstress
4. Musuvathi, M., Qadeer, S.: Iterative context bounding for systematic testing of multithreaded programs. SIGPLAN Not. 42(6), 446–455 (2007)
5. Kokologiannakis, M., Vafeiadis, V.: GenMC: a model checker for weak memory models. In: Silva, A., Leino, K.R.M. (eds.) CAV 2021. LNCS, vol. 12759, pp. 427–440. Springer, Cham (2021). https://doi.org/10.1007/978-3-030-81685-8_20


Our implementation is available on [GitHub](https://github.com/bbrockbernd/MultTest).