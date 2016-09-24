//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation

struct ServiceGroup {
    let serviceTime: String
    let uid: String
    let numberOfAvailablePosition: Double
    let name: String
    let numberOfPeopleInQueue: Double
    let currentServiceNumber: Double
}

extension ServiceGroup {
    init?(dictionary: JSONDictionary) {
        guard let serviceTime = dictionary["czasObslugi"] as? String,
            let uid = dictionary["idGrupy"] as? String,
            let numberOfAvailablePosition = dictionary["liczbaCzynnychStan"] as? Double,
            let name = dictionary["nazwaGrupy"] as? String,
            let numberOfPeopleInQueue = dictionary["liczbaKlwKolejce"] as? Double,
            let currentServiceNumber = dictionary["aktualnyNumer"] as? Double else { return nil }
        self.serviceTime = serviceTime
        self.uid = uid
        self.numberOfAvailablePosition = numberOfAvailablePosition
        self.name = name
        self.numberOfPeopleInQueue = numberOfPeopleInQueue
        self.currentServiceNumber = currentServiceNumber
    }
}
