//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation

struct Service {
    let uid: String
    let serviceGroups: [ServiceGroup]
}

extension Service {
    init?(dictionary: JSONDictionary) {
        guard let serviceData = dictionary["service_data"],
            let serviceDataDictionary = serviceData as? JSONDictionary,
            let serviceGroups = serviceDataDictionary["grupy"],
            let serviceGroupsArray = serviceGroups as? [JSONDictionary],
            let uid = dictionary["department_id"] as? String else { return nil }
        self.serviceGroups = serviceGroupsArray.flatMap(ServiceGroup.init)
        self.uid = uid
    }
}
