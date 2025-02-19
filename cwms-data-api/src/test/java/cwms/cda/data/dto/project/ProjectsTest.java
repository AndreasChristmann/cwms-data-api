/*
 * MIT License
 *
 * Copyright (c) 2024 Hydrologic Engineering Center
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package cwms.cda.data.dto.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import cwms.cda.data.dto.Location;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProjectsTest {

    public static final int PAGE_SIZE = 10;
    public static final int TOTAL = 37;
    public static final String OFFICE = "SPK";

    @Test
    void testPaging() {

        List<Project> projList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Project project = buildProject(i);
            projList.add(project);
        }

        Projects first = new Projects.Builder(null, PAGE_SIZE, TOTAL)
                .addAll(projList)
                .build();

        List<Project> listInObj = first.getProjects();
        assertNotNull(listInObj);
        assertEquals(10, listInObj.size());

        List<String> pages = new ArrayList<>();

        pages.add(first.getPage());

        String nextPage = first.getNextPage();

        String id = Projects.getId(nextPage);
        assertEquals("Test Project9", id, "Expected last project to be 9");
        String office = Projects.getOffice(nextPage);
        assertEquals(OFFICE, office, "Expected office to be SPK");
        int pageSize = Projects.getPageSize(nextPage);
        assertEquals(PAGE_SIZE, pageSize, "Expected page size to be " + PAGE_SIZE);
        int total = Projects.getTotal(nextPage);
        assertEquals(TOTAL, total, "Expected total to be " + TOTAL);

        pages.add(nextPage);
        String currentPage = nextPage;
        projList.clear();

        for (int i = 10; i < 20; i++) {
            Project project = buildProject(i);
            projList.add(project);
        }

        Projects second = new Projects.Builder(currentPage, PAGE_SIZE, TOTAL)
                .addAll(projList)
                .build();

        nextPage = second.getNextPage();

        id = Projects.getId(nextPage);
        assertEquals("Test Project19", id, "Expected last project to be 19");
        office = Projects.getOffice(nextPage);
        assertEquals(OFFICE, office, "Expected office to be SPK");
        pageSize = Projects.getPageSize(nextPage);
        assertEquals(PAGE_SIZE, pageSize, "Expected page size to be " + PAGE_SIZE);
        total = Projects.getTotal(nextPage);
        assertEquals(TOTAL, total, "Expected total to be " + TOTAL);

        pages.add(nextPage);
        currentPage = nextPage;
        projList.clear();

        for (int i = 20; i < 30; i++) {
            Project project = buildProject(i);
            projList.add(project);
        }

        Projects third = new Projects.Builder(currentPage, PAGE_SIZE, TOTAL)
                .addAll(projList)
                .build();


        nextPage = third.getNextPage();

        id = Projects.getId(nextPage);
        assertEquals("Test Project29", id, "Expected last project to be 29");
        office = Projects.getOffice(nextPage);
        assertEquals(OFFICE, office, "Expected office to be " + OFFICE);
        pageSize = Projects.getPageSize(nextPage);
        assertEquals(PAGE_SIZE, pageSize, "Expected page size to be " + PAGE_SIZE);
        total = Projects.getTotal(nextPage);
        assertEquals(TOTAL, total, "Expected total to be " + TOTAL);

        pages.add(nextPage);
        currentPage = nextPage;
        projList.clear();

        for (int i = 30; i < 37; i++) {
            Project project = buildProject(i);
            projList.add(project);
        }

        Projects fourth = new Projects.Builder(currentPage, PAGE_SIZE, 37)
                .addAll(projList)
                .build();

        nextPage = fourth.getNextPage();

        assertNull(nextPage, "Expected no next page");
    }

    private static Project buildProject(int i) {

        return new Project.Builder()
                .withLocation(new Location.Builder(OFFICE, "Test Project" + i)
                        .withTimeZoneName(ZoneId.of("UTC"))
                        .withActive(null)
                        .build())
                .build();
    }
}