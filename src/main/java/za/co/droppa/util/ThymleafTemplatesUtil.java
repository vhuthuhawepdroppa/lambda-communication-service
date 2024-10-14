package za.co.droppa.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import za.co.droppa.dto.Booking;
import za.co.droppa.dto.BucketBooking;
import za.co.droppa.dto.Type;
import za.co.droppa.dto.pudo.PudoBooking;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public final class ThymleafTemplatesUtil {


    public static String bookingCreationTemplate(Booking booking, String name) {
        Map<String, Object> vaules = new HashMap<>();

        String bookingType = booking.getType().toString().replace('_', ' ');
        String bookingTime;

        if (Type.typeServiceLevel(booking.getType()) == 1)
            bookingTime = "1 - 3 days.";
        else if (Type.typeServiceLevel(booking.getType()) == 2)
            bookingTime = "2 - 5 days.";
        else if (Type.typeServiceLevel(booking.getType()) == 3)
            bookingTime = "Same Day.";
        else
            bookingTime = booking.getPickUpTime();

        vaules.put("fullName", name);
        vaules.put("today", DateUtil.getTodayForEmailTemplates());
        vaules.put("trackNo", booking.getTrackNo());
        vaules.put("phoneNo", booking.getPhoneNo());
        vaules.put("comments", booking.getComment());
        vaules.put("bookingType", bookingType);
        vaules.put("numberOfParcels", booking.getLoad());
        vaules.put("bookTime", bookingTime);
        vaules.put("bookDate", booking.getPickUpDate());
        vaules.put("price", "R " + booking.getPrice() + "0");

        String trackNo;

        if (booking.getWaybillResponse() != null) {
            trackNo = booking.getWaybillResponse().getWaybillNumber();
            vaules.put("wayBillNo", booking.getWaybillResponse().getWaybillNumber());

        } else {
            trackNo = booking.getTrackNo();
            vaules.put("wayBillNo", null);
        }

        String canopy;
        if (booking.isCanopy()) {
            canopy = "Canopy is required";
        } else {
            canopy = "No canopy";
        }

        vaules.put("isCanpoy", canopy);
        vaules.put("pickUpAddress", booking.getPickUpAddress());
        vaules.put("dropOffAddress", booking.getDropOffAddress());

        return generateContent("booking-creation", vaules);

    }
    public static String PudobookingCreationConf(PudoBooking booking, String name){
        Map<String, Object> vaules = new HashMap<>();

        String bookingType = booking.getReservationType().toString().replace('_',' ');
        String Btype="";


        String bookingTime = booking.getCreationStamp().toString().substring(0,10);


        vaules.put( "fullName", name);
        vaules.put("today", DateUtil.getTodayForEmailTemplates());
        vaules.put("trackNo", booking.getTransactionId());
        vaules.put("phoneNo", booking.getSenderPhone());
        vaules.put("bookDate", bookingTime);
        if(booking.getFromAddress() != null){
            vaules.put("pickUpAddress", booking.getFromAddress().getAddressline1());
        }else{
            vaules.put("pickUpAddress", booking.getFromterminalAddress());
        }
        if(booking.getToAddress() != null){
            vaules.put("dropOffAddress", booking.getToAddress().getAddressline1());
        }else{
            vaules.put("dropOffAddress", booking.getToterminalAddress());
        }
        if(bookingType.equals("L2L")){
            Btype = "Locker to Locker";
            vaules.put("bookingType", Btype);
        }else if(bookingType.equals("D2L"))
        {
            Btype ="Door to Locker";
            vaules.put("bookingType", Btype);
        }else{
            Btype = "Locker to Door";
            vaules.put("bookingType", Btype);
        }

        vaules.put("wayBillNo", booking.getWayBillNumber());
        vaules.put("PIN", booking.getPin());
        vaules.put("CollectionNo", booking.getCollectionNumber());
        if(booking.getToTerminalId() != null){
            vaules.put("ToLocker", booking.getToTerminalId());
        }
        if(booking.getFromTerminalId()!=null){
            vaules.put("FromLocker", booking.getFromTerminalId());
        }
        vaules.put("trackNo", booking.getTransactionId());

        vaules.put("price","R " +booking.getPrice()+"0");


        return  generateContent("pudo-booking-confirmation",vaules);

    }

    public static String bookingCompleteEmailTemplate(Booking b,String name){
        Map<String, Object> values = new HashMap<>();

        values.put("price","R "+b.getPrice());
        values.put("trackNo",b.getTrackNo());
        values.put("fromAddress",b.getPickUpAddress());
        values.put("toAddress",b.getPickUpAddress());
        values.put("canopy",b.isCanopy()? "YES" : "NONE");
        values.put("contact",b.getPhoneNo());
        values.put("waybill",b.getWaybillResponse().getWaybillNumber());
        values.put("packages",b.getParcelDimensions().size());
        values.put("clientName",name);
        values.put("collectionDate", b.getBookingCreatedDate().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        values.put("today",DateUtil.getTodayForEmailTemplates());
        String eta="1-3 days";

        values.put("eta","1-5 days");

        return generateContent("booking-complete-details",values);
    }
    public static String PudobookingCreationTemplate(PudoBooking booking, String name){
        Map<String, Object> vaules = new HashMap<>();

        String bookingType = booking.getReservationType().toString().replace('_',' ');
        String Btype="";
        String bookingTime = booking.getCreationStamp().toString().substring(0,10);

        vaules.put( "fullName", name);
        vaules.put("today", DateUtil.getTodayForEmailTemplates());
        vaules.put("trackNo", booking.getTransactionId());
        vaules.put("phoneNo", booking.getSenderPhone());
        vaules.put("bookDate", bookingTime);
        if(booking.getFromAddress() != null){
            vaules.put("pickUpAddress", booking.getFromAddress().getAddressline1());
        }else{
            vaules.put("pickUpAddress", booking.getFromterminalAddress());
        }
        if(booking.getToAddress() != null){
            vaules.put("dropOffAddress", booking.getToAddress().getAddressline1());
        }else{
            vaules.put("dropOffAddress", booking.getToterminalAddress());
        }
        if(bookingType.equals("L2L")){
            Btype = "Locker to Locker";
            vaules.put("bookingType", Btype);
        }else if(bookingType.equals("D2L"))
        {
            Btype ="Door to Locker";
            vaules.put("bookingType", Btype);
        }else{
            Btype = "Locker to Door";
            vaules.put("bookingType", Btype);
        }

        vaules.put("price", "R " +booking.getPrice()+"0");

        vaules.put("trackNo", booking.getTransactionId());

        return  generateContent("pudo-booking-creation",vaules);

    }

    //booking done email
    public  static String completeBookingTemplate(String name, String pickUp, String dropOff, String url){

        Map<String, Object> vaules = new HashMap<>();

        vaules.put( "fullName", name);
        vaules.put("pickUpAddress", pickUp);
        vaules.put("dropOffAddress", dropOff);
        vaules.put("rateLink", url);
        vaules.put("today",DateUtil.getTodayForEmailTemplates());

        return generateContent("booking-complete",vaules);
    }

    //booking cancelled
    public static String cancelBookingTemplate(String name, String pickUp, String dropOff){

        Map<String, Object> vaules = new HashMap<>();

        vaules.put( "fullName", name);
        vaules.put("pickUpAddress", pickUp);
        vaules.put("dropOffAddress", dropOff);
        vaules.put("today",DateUtil.getTodayForEmailTemplates());

        return generateContent("booking-cancelled",vaules);
    }

    //Parnter confirmation email
    public  static String partnerRegistrationTemplate(String name){

        Map<String, Object> vaules = new HashMap<>();
        vaules.put( "fullName", name);


        vaules.put("today",DateUtil.getTodayForEmailTemplates());
       return generateContent("partner-registration",vaules);
    }
    //Parnter confirmation email
    public  static String walletLoadTemplate(String name, double amount){
        Map<String, Object> vaules = new HashMap<>();
        vaules.put( "fullName", name);

        vaules.put( "amount", amount+"0");
        vaules.put( "today", DateUtil.getTodayForEmailTemplates());

        return generateContent("wallet-load",vaules);
    }
    public  static String emailOTP(String name, String otp){

        Map<String, Object> vaules = new HashMap<>();
        vaules.put( "fullName", name);
        vaules.put( "otp", otp);
        vaules.put("today", DateUtil.getTodayForEmailTemplates());
        return generateContent("email-otp-activation",vaules);
    }

    public  static String RetailActivationTemplate(String name, double amount){

        Map<String, Object> vaules = new HashMap<>();
        vaules.put( "fullName", name);

        vaules.put( "amount", amount+"0");
        vaules.put( "today", DateUtil.getTodayForEmailTemplates());
        return generateContent("retail-activation",vaules);
    }

    //Retail Booking Email
    public static String retailBookingCreationTemplate(BucketBooking booking, String name){

        Map<String, Object> vaules = new HashMap<>();

        String bookingType = booking.getType().toString().replace('_',' ');
        vaules.put( "retailName", booking.getRetail().getName());
        vaules.put("today", DateUtil.getTodayForEmailTemplates());
        vaules.put("trackNo", booking.getTrackNo());
        vaules.put("phoneNo", booking.getRetail().getOwner().getMobile());
        vaules.put("comments", booking.getComments());
        vaules.put("bookingType", bookingType);
        vaules.put("deliveries", booking.getBookings().size());
        vaules.put("bookTime", booking.getTime());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        vaules.put("bookDate", formatter.format(booking.getDate()));
        vaules.put("price", "R " +booking.getPrice()+"0");

        vaules.put("isCanpoy","");
        vaules.put("pickUpAddress", booking.getBookings().get(0).getPickUpAddress());


        return generateContent("retail-booking-creation",vaules);
    }


    //Method used to generate the template with the correct information passed
    public static String generateContent(String templatePath, Map <String, Object> variables){
        StringWriter writer =  new StringWriter();

        Context context = new Context(Locale.US);

        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver());

        context.setVariables(variables);
        engine.process(templatePath, context, writer);


        return writer.toString();
    }


    public static ClassLoaderTemplateResolver templateResolver() {
        ClassLoader classLoader = ThymleafTemplatesUtil.class.getClassLoader();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver(classLoader);
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

}
