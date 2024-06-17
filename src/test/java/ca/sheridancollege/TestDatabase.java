package ca.sheridancollege;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.beans.Mission;
import ca.sheridancollege.database.DatabaseAccess;

@SpringBootTest
class TestDatabase {

    private DatabaseAccess da;

    @Autowired
    public void setDa(DatabaseAccess da) {
        this.da = da;
    }

    @BeforeEach
    public void setup() {
        Mission mission = new Mission();
        mission.setAgent("Johnny English");
        mission.setTitle("Rescue the Queen");
        mission.setGadget1("Gadget1");
        mission.setGadget2("Gadget2");
        da.addMission(mission);
    }

    @Test
    public void testDatabaseAddMission() {
        Mission mission = new Mission();
        mission.setAgent("Starlord");
        mission.setGadget1("The element gun");
        mission.setGadget2("Sub-machine gun");
        mission.setTitle("Peter Quill");

        int origSize = da.getMissions("Starlord").size();

        da.addMission(mission);
        int newSize = da.getMissions("Starlord").size();

        assertEquals(newSize, origSize + 1);
    }

    @Test
    public void testDatabaseDeleteMission() {
        List<Mission> missions = da.getMissions("Johnny English");

        assertFalse(missions.isEmpty(), "No missions found for Johnny English");

        Long id = missions.get(0).getId();

        int origSize = da.getMissions(missions.get(0).getAgent()).size();

        da.deleteMission(id);
        int newSize = da.getMissions(missions.get(0).getAgent()).size();

        assertEquals(newSize, origSize - 1);
    }

    @Test
    public void testDatabaseUpdateMission() {
        List<Mission> missions = da.getMissions("Johnny English");

        assertFalse(missions.isEmpty(), "No missions found for Johnny English");

        Mission mission = missions.get(0);
        Long id = mission.getId();

        mission.setTitle("Rescue the world");
        da.updateMission(mission);

        mission = da.getMission(id);

        assertEquals(mission.getTitle(), "Rescue the world");
    }

    @Test
    public void testDatabaseGetMission() {
        List<Mission> missions = da.getMissions("Johnny English");

        assertFalse(missions.isEmpty(), "No missions found for Johnny English");

        Mission mission = da.getMission(missions.get(0).getId());

        assertEquals(mission.getTitle(), "Rescue the Queen");
    }

    @Test
    public void testDatabaseGetMissions() {
        List<Mission> missions = da.getMissions("Johnny English");

        assertFalse(missions.isEmpty(), "No missions found for Johnny English");

        Mission mission = missions.get(0);

        assertEquals(mission.getTitle(), "Rescue the Queen");
    }
}
