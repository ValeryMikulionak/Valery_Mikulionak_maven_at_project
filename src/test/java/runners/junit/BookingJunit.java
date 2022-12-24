package runners.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.junit.RegisterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({RegisterTest.class})
public class BookingJunit {
}