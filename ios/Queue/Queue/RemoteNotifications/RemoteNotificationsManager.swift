//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import Foundation
import UIKit
import UserNotifications

final class RemoteNotificationsManager {
    static func register(result: @escaping (Bool) -> ()) {
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .sound, .badge]) { success, error in
            DispatchQueue.main.async {
                if let error = error {
                    print("Error when requesting authorization: %@", error)
                } else {
                    UIApplication.shared.registerForRemoteNotifications()
                }
                result(success)
            }
        }
    }
}
