//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation

struct Deparment {
    let address: Address
    let cityId: Double
    let uid: String
    let location: Location
    let name: String
    let openingHours: OpeningHours
    let phone: String
}

extension Deparment {
    init?(dictionary: JSONDictionary) {
        guard let addressDictionary = dictionary["address"] as? JSONDictionary,
            let address = Address(dictionary: addressDictionary),
            let cityId = dictionary["city_id"] as? Double,
            let uid = dictionary["department_id"] as? String,
            let locationDictionary = dictionary["location"] as? JSONDictionary,
            let location = Location(dictionary: locationDictionary),
            let name = dictionary["name"] as? String,
            let openingHoursDictionary = dictionary["opening_hours"] as? JSONDictionary,
            let openingHours = OpeningHours(dictionary: openingHoursDictionary),
            let phone = dictionary["phone"] as? String else { return nil }
        self.address = address
        self.cityId = cityId
        self.uid = uid
        self.location = location
        self.name = name
        self.openingHours = openingHours
        self.phone = phone
    }
}
