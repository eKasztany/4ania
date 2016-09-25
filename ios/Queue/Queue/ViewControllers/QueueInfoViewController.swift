//
//  Created by Aleksander Grzyb on 25/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit
import UserNotifications
import UserNotificationsUI

class QueueInfoViewController: UIViewController {

    @IBOutlet weak var numberOfPeopleInQueue: UILabel!
    @IBOutlet weak var currentNumber: UILabel!
    @IBOutlet weak var estimatedTimeOfService: UILabel!

    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet var timeButtons: [UIButton]!
    @IBOutlet weak var actionButton: UIButton!

    var service: ServiceGroup?
    override func viewDidLoad() {
        super.viewDidLoad()
        updateService()
        configureBarButtonItem()
        configureButtons(hidden: true)
    }

    private func updateService() {
        guard let service = service else { return }
        numberOfPeopleInQueue.text = String(format: "%.0f", service.numberOfPeopleInQueue)
        currentNumber.text = "aktualny numer = " + String(format: "%.0f", service.currentServiceNumber)
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
        let _ = estimatedTimeDouble - Double(estimatedMinutes * 60)
        return String(format: "%d", estimatedMinutes) + " minut"
    }

    private func configureButtons(hidden: Bool) {
        if hidden {
            descriptionLabel.text = "Nie musisz być w urzędzie, wystarczy, że dołączysz przez aplikacje."
        } else {
            descriptionLabel.text = "Przypomnij mi jak szacowany czas oczekiwania będzie równy:"
        }
        for timeButton in timeButtons {
            timeButton.isHidden = hidden
            timeButton.layer.cornerRadius = 5.0
        }
        actionButton.isHidden = !hidden
        actionButton.layer.cornerRadius = 5.0
    }

    func showData() {
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

    @IBAction func joinQueueOptionChanged(_ sender: UISegmentedControl) {
        if sender.selectedSegmentIndex == 0 {
            configureButtons(hidden: true)
        } else {
            configureButtons(hidden: false)
        }
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
