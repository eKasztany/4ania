//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation
import Firebase

typealias JSONDictionary = [String: Any]

struct Resource<A> {
    let path: String
    let parse: (Any?) -> A?
}

extension Deparment {
    static let all = Resource<[Deparment]>(path: "departments", parse: { json in
        guard let dictionaries = json as? [JSONDictionary] else { return nil }
        return dictionaries.flatMap(Deparment.init)
    })
}

final class FirebaseClient {
    func load<A>(resource: Resource<A>, completion: @escaping (A?) -> ()) {
        let ref = FIRDatabase.database().reference()
        ref.child(resource.path).observeSingleEvent(of: .value, with: { snapshot in
            guard let data = snapshot.value else { return completion(nil) }
            completion(resource.parse(data))
        }) { error in
            completion(nil)
        }
    }
}