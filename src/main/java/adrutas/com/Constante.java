package adrutas.com;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public final class Constante {
    private static final Logger log = Logger.getLogger(Constante.class.getName());
    public static final TimeZone tZ = TimeZone.getTimeZone("Europe/Madrid");
    public static final Locale locale = new Locale("es", "ES");
    public static final NumberFormat nF1 = new DecimalFormat("00");
    public static final NumberFormat nF2 = new DecimalFormat("0000");
    public static final NumberFormat nF3 = new DecimalFormat("00000000");
    public static final NumberFormat nF4 = new DecimalFormat("#,##0.00",DecimalFormatSymbols.getInstance(locale));
    public static final DateFormat dF1 = new SimpleDateFormat("yyyyMMdd_hhmmss");
    public static final DateFormat dF2 = new SimpleDateFormat("yy/MM/dd HH:mm:ss", locale);
    public static final DateFormat dF3 = new SimpleDateFormat("yyyyMMdd", locale);
    public static final DateFormat dF4 = new SimpleDateFormat("dd/MM/yyyy", locale);
    public static final DateFormat dF5 = new SimpleDateFormat("yyMMdd");
    public static final DateFormat dF6 = new SimpleDateFormat("d 'de' ");
    public static final DateFormat dF7 = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
    public static final DateFormat dF8 = new SimpleDateFormat("dd-MMM", new Locale("es", "ES"));
    public static final DateFormat dF9 = new SimpleDateFormat("dd/MM/yyyy HH:mm", locale);
    public static final DateFormat dF10 = new SimpleDateFormat("HH:mm", locale);
    public static final DateFormat dF11 = new SimpleDateFormat("/yyyy/MMdd/");
    public static final DateFormat dF12 = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat dF13 = new SimpleDateFormat("d 'de' MMMM");
    public static int FICHA_YEAR;
    public static final String LETRAS_NIE = "TRWAGMYFPDXBNJZSQVHLCKE";
    public static final String INICIO_NIE = "XYZ";
    public static final Pattern PATTERN_NOMBRE = Pattern.compile("[\\p{IsL} ]+");
    public static final Pattern PATTERN_NIF1 = Pattern.compile("\\p{Digit}{1,8}|\\p{Digit}{1,8}[" + LETRAS_NIE + "]");
    public static final Pattern PATTERN_NIF2 = Pattern.compile("\\p{Digit}{1,8}[" + LETRAS_NIE + "]");
    public static final Pattern PATTERN_NIE1 = Pattern.compile(
                    "[XYZ]\\p{Digit}{1,7}|[XYZ]\\p{Digit}{7}[" + LETRAS_NIE + "]");
    public static final Pattern PATTERN_NIE2 = Pattern.compile("[XYZ]\\p{Digit}{7}[" + LETRAS_NIE + "]");
    public static final Pattern PATTERN_NIE3 = Pattern.compile("[XYZ]\\p{Digit}{1,7}[" + LETRAS_NIE + "]");
    public static final Pattern PATTERN_NIF_SIN = Pattern.compile("\\p{Digit}{1,8}");
    public static final Pattern PATTERN_NIE_SIN = Pattern.compile("[XYZ]\\p{Digit}{1,7}");
    public static final Pattern PATTERN_TELEFONO = Pattern.compile("\\p{Digit}{1,9}");
    public static final Pattern PATTERN_ID_PERSONA = Pattern.compile("#\\p{Digit}{4}");
    public static final Pattern PATTERN_EMAIL = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public static final Pattern PATTERN_INI_EMAIL = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
    		+ "(@([A-Za-z0-9-]+((\\.[A-Za-z0-9]+)*(\\.[A-Za-z]*))?)?)?$");
    public static final Random r = new Random(new java.util.GregorianCalendar().getTimeInMillis());
    public static final Map<Integer, Boolean> anyosOpen = new HashMap<Integer, Boolean>();
    public static final Map<Pattern, List<DateFormat>> FORMATOS_FECHAS = new HashMap<>();
    public static final int numGS = 12;
    public static final Set<String> ORDINARIAS = new HashSet<String>(Arrays.asList(new String[] {"BO", "ME", "TA", "TF"}));
    public static final Set<String> SIN_IMPORTE = new HashSet<String>(Arrays.asList(
                    new String[] {"", "AN", "BO", "FO", "GN", "GS"}));
    public static final Pattern PATTERN_CEROS = Pattern.compile("^0*");

	public static void init() {
        TimeZone.setDefault(tZ);
//        Locale.setDefault(locale);
        List<DateFormat> list;
        list = new ArrayList<>();
        list.add(new SimpleDateFormat("dd-MM-yy"));
        list.add(new SimpleDateFormat("yy-MM-dd"));
        FORMATOS_FECHAS.put(Pattern.compile("\\p{Digit}{2}-\\p{Digit}{2}-\\p{Digit}{2}"), list);
        list = new ArrayList<>();
        list.add(new SimpleDateFormat("dd-MM-yyyy"));
        FORMATOS_FECHAS.put(Pattern.compile("\\p{Digit}{2}-\\p{Digit}{2}-\\p{Digit}{4}"), list);
        list = new ArrayList<>();
        list.add(new SimpleDateFormat("yyyy-MM-dd"));
        FORMATOS_FECHAS.put(Pattern.compile("\\p{Digit}{4}-\\p{Digit}{2}-\\p{Digit}{2}"), list);
        list = new ArrayList<>();
        list.add(new SimpleDateFormat("dd/MM/yy"));
        list.add(new SimpleDateFormat("yy/MM/dd"));
        FORMATOS_FECHAS.put(Pattern.compile("\\p{Digit}{2}/\\p{Digit}{2}/\\p{Digit}{2}"), list);
        list = new ArrayList<>();
        list.add(new SimpleDateFormat("dd/MM/yyyy"));
        FORMATOS_FECHAS.put(Pattern.compile("\\p{Digit}{2}/\\p{Digit}{2}/\\p{Digit}{4}"), list);
        list = new ArrayList<>();
        list.add(new SimpleDateFormat("yyyy/MM/dd"));
        FORMATOS_FECHAS.put(Pattern.compile("\\p{Digit}{4}/\\p{Digit}{2}/\\p{Digit}{2}"), list);
	}
}