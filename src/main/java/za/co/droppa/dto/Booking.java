package za.co.droppa.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Booking extends PersistentObject {

    private String dropOffOid;

    private String pickUpOid;

    private String userOid;

    private String vehicleType;

	private boolean isBucket;

    private String pickUpAddress;

    private String pickUpDate;

    private String pickUpTime;

    private String dropOffAddress;

	private String skynetStatus;

    private double price;

    private String phoneNo;

    private int labour;

    private String comment;

    private String timestamp;

    private boolean canopy;

    private int load;

    private BookingStatus status;

    private int pickUpFloors;

    private int dropFloors;

    private String driverOid;

    private String dropOid;

    private boolean movedToExpress;

    private String trackNo;

    private boolean isPayed;


    private String platform;

    private String promotionCode;

	private String toEmail;

    private BookingPaymentType paymentType;
    
    private LocalDateTime bookingCreatedDate;

	private boolean isExpress;

	private int distance;

	private double itemMass;

	private boolean isItemPicked;

	private TransportMode transportMode;

	private String mainCityOid;

	private Type type;

	private double pickUpAmt;

	private double airlineAmt;

	private double dropOffAmt;

	private String parentBookingOid;

	private String returnComments;

	private String destinationProvince;

	private String fromSuburb;

	private String toSuburb;

	private String pickUpPCode;

	private String dropOffPCode;

	private WaybillResponse waybillResponse;

	private String manualWaybill;


	private boolean isPaidViaWallet;

	private String customerReference;

	private String costCenter;

	private List<ParcelDimension> parcelDimensions;

	private List<BookingParcel> parcels;

	private String shopify_orderNo;


	private LocalDateTime expectedDeliveryDate;

	private List<CommodityItems> commodityItems;


	/*Linda
	16/08/2023*/
	private boolean isTender;

	/*
	Terrence
	26/06/2022
	 */
	private String retailId;

	//20/10/2022
	private boolean isDashBike;

	/*
	Terrence
	10/01/203
	 */
	private String tipOid;


	private String toAlternativeContactName;
	private String toAlternativeContactNumber;
	private boolean isUber;

	public SkynetInsurance insurance;

	private String companyName;

	private boolean priority;

	private boolean emailed;
	private String fromCity;
	private String toCity;

	private String regionOid;


	private boolean isReceiverPaying;

	private BookingMetaData metaData;

	private LocalDateTime creationStamp;
}
