package alexrnov.androidsamples

import alexrnov.androidsamples.parcelable.StudentTest
import alexrnov.androidsamples.services.ServiceTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * To organize the execution of your instrumented unit tests, you can group
 * a collection of test classes in a test suite class and run these tests together.
 */
@RunWith(Suite::class)
// перечисляются классы, которые нужно выполнить вместе
@Suite.SuiteClasses(StudentTest::class, ServiceTest::class)
class UnitTestSuite