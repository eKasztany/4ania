//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation
import MapKit

class Department: NSObject {
    var address: Address
    var cityId: Double
    var uid: String
    var location: Location
    var name: String
    var openingHours: OpeningHours
    var phone: String
    var desc: String

    override init() {
        self.address = Address(city: "", code: "", street: "")
        self.cityId = 0
        self.uid = ""
        self.location = Location(lat: 0, lon: 0)
        self.name = ""
        self.openingHours = OpeningHours(mon: "", tue: "", wen: "", thr: "", fri: "")
        self.phone = ""
        self.desc = ""
    }
}

extension Department {
    convenience init?(dictionary: JSONDictionary) {
        self.init()
        guard let addressDictionary = dictionary["address"] as? JSONDictionary,
            let address = Address(dictionary: addressDictionary),
            let cityId = dictionary["city_id"] as? Double,
            let uid = dictionary["department_id"] as? String,
            let locationDictionary = dictionary["location"] as? JSONDictionary,
            let location = Location(dictionary: locationDictionary),
            let name = dictionary["name"] as? String,
            let openingHoursDictionary = dictionary["opening_hours"] as? JSONDictionary,
            let openingHours = OpeningHours(dictionary: openingHoursDictionary),
            let phone = dictionary["phone"] as? String,
            let desc = dictionary["description"] as? String else { return nil }
        self.address = address
        self.cityId = cityId
        self.uid = uid
        self.location = location
        self.name = name
        self.openingHours = openingHours
        self.phone = phone
        self.desc = desc
    }
}

extension Department: MKAnnotation {

    var coordinate: CLLocationCoordinate2D {
        return CLLocationCoordinate2DMake(location.lat, location.lon)
    }

    var title: String? {
        return name
    }

    var subtitle: String? {
        return "\(address.street), \(address.code)"
    }
}
