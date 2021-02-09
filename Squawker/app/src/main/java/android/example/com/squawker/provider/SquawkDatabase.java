package android.example.com.squawker.provider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;


@Database(version = SquawkDatabase.VERSION)
public class SquawkDatabase {

    public static final int VERSION = 4;

    @Table(SquawkContract.class)
    public static final String SQUAWK_MESSAGES = "squawk_messages";

}
