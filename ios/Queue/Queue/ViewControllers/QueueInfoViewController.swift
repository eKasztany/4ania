//
//  Created by Aleksander Grzyb on 25/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit
import UserNotifications
import UserNotificationsUI

class QueueInfoViewController: UIViewController {

    @IBOutlet weak var numberOfPeopleInQueue: UILabel!
    @IBOutlet weak var numberOfOpenPositions: UILabel!
    @IBOutlet weak var currentNumber: UILabel!
    @IBOutlet weak var averageTimeForService: UILabel!
    @IBOutlet weak var estimatedTimeOfService: UILabel!

    @IBOutlet weak var minutesTextField: UITextField!
    @IBOutlet weak var peopleTextField: UITextField!
    @IBOutlet weak var minutesLabel: UILabel!
    @IBOutlet weak var peopleLabel: UILabel!

    var service: ServiceGroup?
    override func viewDidLoad() {
        super.viewDidLoad()
        updateService()
        configureBarButtonItem()
    }

    private func updateService() {
        guard let service = service else { return }
        numberOfPeopleInQueue.text = String(format: "%.0f", service.numberOfPeopleInQueue)
        numberOfOpenPositions.text = String(format: "%.0f", service.numberOfAvailablePosition)
        currentNumber.text = String(format: "%.0f", service.currentServiceNumber)
        if let _ = service.serviceTime.range(of: ":") {
            let serviceTimeWithMin = service.serviceTime.replacingOccurrences(of: ":", with: " min ")
            averageTimeForService.text = serviceTimeWithMin.appending(" s")
        } else {
            averageTimeForService.text = "00 min 0 s"
        }
        estimatedTimeOfService.text = calculateEstimatedTime()
    }

    private func calculateEstimatedTime() -> String {
        guard let service = service, let splitPoint = service.serviceTime.range(of: ":") else { return "0" }
        let minutes = service.serviceTime.substring(to: splitPoint.lowerBound)
        let seconds = service.serviceTime.substring(from: splitPoint.upperBound)
        let minutesNumber = NSDecimalNumber(string: minutes)
        let secondsNumber = NSDecimalNumber(string: seconds)
        let totalSeconds = secondsNumber.adding(minutesNumber.multiplying(by: NSDecimalNumber(string: "60")))
        let peoplePerPosition = service.numberOfPeopleInQueue / service.numberOfAvailablePosition
        let estimatedTime = totalSeconds.multiplying(by: NSDecimalNumber(value: peoplePerPosition))
        let estimatedTimeDouble = estimatedTime.doubleValue
        let estimatedMinutes = Int(estimatedTimeDouble / 60)
        let estimatedSeconds = estimatedTimeDouble - Double(estimatedMinutes * 60)
        return String(format: "%d", estimatedMinutes) + " min " + String(format: "%.0f", estimatedSeconds) + " s"
    }

    private func configureBarButtonItem() {
        let barButtonItem = UIBarButtonItem(title: "\u{f080}",
                                            style: .plain,
                                            target: self,
                                            action: #selector(showData))
        let attributes = [NSFontAttributeName: UIFont.init(name: "FontAwesome", size: 20.0)!]
        barButtonItem.setTitleTextAttributes(attributes, for: .normal)
        navigationItem.rightBarButtonItem = barButtonItem
    }

    @IBAction func peopleNotification(_ sender: UIButton) {
        sender.setTitle("Ustawiono!", for: .normal)
        sender.setTitleColor(.green, for: .normal)
        sender.isEnabled = false
        peopleLabel.alpha = 0.0
        peopleTextField.alpha = 0.0
        peopleTextField.isEnabled = false
        scheduleLocalNotification(message: "Zostało już 8 osób w kolejce.")
    }

    @IBAction func timeNotification(_ sender: UIButton) {
        sender.setTitle("Ustawiono!", for: .normal)
        sender.setTitleColor(.green, for: .normal)
        sender.isEnabled = false
        minutesLabel.alpha = 0.0
        minutesTextField.alpha = 0.0
        minutesTextField.isEnabled = false
        scheduleLocalNotification(message: "Zostało już 5 minut do estymowanego czasu obsłużenia.")
    }

    @IBAction func myTurnNotificaton(_ sender: UIButton) {
        sender.setTitle("Ustawiono!", for: .normal)
        sender.setTitleColor(.green, for: .normal)
        sender.isEnabled = false
        scheduleLocalNotification(message: "Nadeszła Twoja kolej! Podejdź do okienka.")
    }

    func showData() {

    }

    private func scheduleLocalNotification(message: String) {
        guard let service = service else { return }
        let content = UNMutableNotificationContent()
        content.title = "\(service.name)"
        content.body = message
        let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 5, repeats: false)
        let requestIdentifier = "sampleRequest"
        let request = UNNotificationRequest(identifier: requestIdentifier,
                                            content: content,
                                            trigger: trigger)
        UNUserNotificationCenter.current().add(request) { (error) in }
    }
}
