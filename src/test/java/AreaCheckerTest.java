import managers.AreaChecker;
import managers.AreaCheckerService;
import managers.PointsRepository;
import managers.beans.DataSaverBean;
import managers.beans.DatabaseControllerBean;
import managers.beans.FormControllerBean;
import managers.beans.MethodControllerBean;
import managers.dataModels.Dot;
import managers.dataModels.Result;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.faces.context.FacesContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AreaCheckerTest {

    @InjectMocks
    DataSaverBean dataSaverBean;

    @InjectMocks
    @Spy
    FormControllerBean formControllerBean;

    @Mock
    MethodControllerBean methodControllerBean;

    @Mock
    AreaCheckerService areaCheckerService;

    @Mock
    PointsRepository pointsRepository;

    @Mock
    DatabaseControllerBean db;

    @BeforeClass
    public static void setUpClass() {
        FacesContext mockContext = Mockito.mock(FacesContext.class);
        Mockito.mockStatic(FacesContext.class).when(FacesContext::getCurrentInstance).thenReturn(mockContext);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCheckArea() {
        AreaChecker checker = new AreaChecker();

        assertTrue(checker.checkSpot(0.1, 0.3, 3));
        assertFalse(checker.checkSpot(0.6, -2.3, 2));
        assertTrue(checker.checkSpot(-0.5, -0.3, 2));
        assertFalse(checker.checkSpot(-0.3, 0.3, 2));
    }

    @Test
    public void testSendCorrectData() {
        doNothing().when(formControllerBean).resetFormState();

        String x = "1.0", y = "1.0", r = "3.0";

        formControllerBean.setSelectedX(x);
        formControllerBean.setSelectedY(y);
        formControllerBean.setSelectedR(r);

        formControllerBean.submit();

        verify(methodControllerBean).handleRequest(any(Dot.class));
    }

    @Test
    public void testSendIncorrectData() {
        doNothing().when(formControllerBean).resetFormState();

        formControllerBean.setSelectedX("5.0");
        formControllerBean.setSelectedY("1.0");
        formControllerBean.setSelectedR("3.0");
        formControllerBean.submit();
        verify(methodControllerBean, never()).handleRequest(any(Dot.class));

        formControllerBean.setSelectedX("1.0");
        formControllerBean.setSelectedY("-5.5");
        formControllerBean.setSelectedR("3.0");
        formControllerBean.submit();
        verify(methodControllerBean, never()).handleRequest(any(Dot.class));

        formControllerBean.setSelectedX("1.0");
        formControllerBean.setSelectedY("1.0");
        formControllerBean.setSelectedR("0.0");
        formControllerBean.submit();
        verify(methodControllerBean, never()).handleRequest(any(Dot.class));
    }

    @Test
    public void testSaveResult() {
        Dot dot = new Dot(1, 1, 1);
        Result mockResult = Result.createResult(dot, true, 1.0, "12:00:00");
        when(areaCheckerService.processDot(dot)).thenReturn(mockResult);

        dataSaverBean.saveData(dot);

        verify(areaCheckerService).processDot(dot);
        verify(pointsRepository).addResult(mockResult);
        verify(db).saveResult(mockResult);
        assertEquals(mockResult, dataSaverBean.getResult());
    }
}