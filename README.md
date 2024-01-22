# SkytecGamesTestTask
A test case for multithreading using Java.

**The main goal of the project**: to implement multi-threaded secure update of the Gold field in the `Clan` class.
Multithreading is implemented using `ExecutorService`. Synchronization and atomicity of operations with the `Gold` field - through the use of `AtomicInteger`.

The `atomic class` is chosen as a means of synchronization because it provides **optimal performance for frequent reads and writes** of the class field compared to other means of synchronization, e.g. `synchronized()`, `ReentantLock`.
Each field change is logged with the reason for the update, the original value and the updated value.

To make it easier to test multithreading, I put the ability to test in the `MainMultithreadingPresentation` class, without having to bring up the server via `Apache Tomcat`.

The project was prohibited to use `Spring, Hibernate`. As a consequence, the `JAX-RS` specification in `Jeksey` implementation and `Apache Tomcat` web server were used to develop the REST part of the application.
I don't have much experience with `Java EE` tools, so don't judge strictly :).

---

Тестовый пример многопоточности с использованием Java.

**Основная цель проекта**: реализовать многопоточное безопасное обновление поля Gold в классе `Clan`.
Многопоточность реализована с помощью `ExecutorService`. Синхронизация и атомарность операций с полем `Gold` - за счет использования `AtomicInteger`.

В качестве средства синхронизации выбран `атомарный класс`, так как он **обеспечивает оптимальную производительность при частых чтениях и записях** поля класса по сравнению с другими средствами синхронизации, например, `synchronized()`, `ReentantLock`.
Каждое изменение поля записано в логи с указанием причины обновления, исходным и обновленным значением. 

В проекте было запрещено использовать `Spring, Hibernate`. Как следствие, для разработки REST части приложения была использована спецификация `JAX-RS` в реализации `Jeksey` и веб-сервер `Apache Tomcat`.
У меня нет большого опыта работы с инструментами `Java EE`, так что не судите строго :).
